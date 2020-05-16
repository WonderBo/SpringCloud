package com.spring.cloud.user.controller;

import com.spring.cloud.user.VO.ResultVO;
import com.spring.cloud.user.bean.UserInfo;
import com.spring.cloud.user.constant.CookieConstant;
import com.spring.cloud.user.constant.RedisConstant;
import com.spring.cloud.user.enums.ResultEnum;
import com.spring.cloud.user.enums.RoleEnum;
import com.spring.cloud.user.service.UserService;
import com.spring.cloud.user.utils.CookieUtil;
import com.spring.cloud.user.utils.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author 汪波
 * @Date 2020/5/16 19:08
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     *
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        // 1、openid和数据库里数据进行匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2、判断角色
        if (!RoleEnum.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3、cookie设置openid=xxx
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }

    /**
     * 卖家登录
     *
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        // 0、判断是否已登录（redis是否存放token信息）
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null &&
                StringUtils.isNotEmpty(stringRedisTemplate.opsForValue()
                        .get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        // 1、openid和数据库里数据进行匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2、判断角色
        if (!RoleEnum.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3、redis设置key=UUID，value=openid
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

        // 4、cookie设置openid=xxx
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return ResultVOUtil.success();
    }
}
