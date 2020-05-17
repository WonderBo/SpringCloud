package com.spring.cloud.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.spring.cloud.apigateway.constant.CookieConstant;
import com.spring.cloud.apigateway.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限校验（前置）Filter（每个角色对应一个权限校验Filter，避免角色权限间的耦合）
 * 注意网关服务和用户服务的边界问题（避免直连其他服务数据库）
 * 网关服务访问用户服务需要注意性能问题（可以读取redis + 用户信息修改发送异步消息，网关监听此消息并同步到redis）
 *
 * 微服务框架中多个服务的无状态化方案：
 * 1、分布式Session（用户认证信息保存在共享储存中，通常用户会话作为key实现简单的分布式hash映射）【高可用、可扩展】
 * 2、OAuth2（结合Spring Security）
 *
 * 公用组件 -> 公用模块 -> 公用服务
 *
 * @Author 汪波
 * @Date 2020/5/16 18:17
 **/
@Component
public class AuthBuyerFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        if ("/order/order/create".equals(request.getRequestURI())) {
            return true;
        }

        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        /**
         * /order/create 只能由买家访问（cookie中有openid标志）
         */
        Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
        if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
