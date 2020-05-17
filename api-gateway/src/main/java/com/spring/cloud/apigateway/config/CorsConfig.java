package com.spring.cloud.apigateway.config;

/**
 * Zuul跨域配置，解决跨域问题，对整个服务框架生效
 * C - Cross; O - Origin; R - Resource; S - Sharing
 *
 * @Author 汪波
 * @Date 2020/5/17 22:17
 **/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        // 是否支持Cookie跨域
        config.setAllowCredentials(true);
        // 支持原始域
        config.setAllowedOrigins(Arrays.asList("*"));
        // 允许头
        config.setAllowedHeaders(Arrays.asList("*"));
        // 允许方法类型
        config.setAllowedMethods(Arrays.asList("*"));
        // 缓存最大时间（相同跨域请求在此时间范围内不再检查）
        config.setMaxAge(300L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
