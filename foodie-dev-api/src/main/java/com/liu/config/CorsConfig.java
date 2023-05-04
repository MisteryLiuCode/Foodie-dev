package com.liu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liushuaibiao
 * @date 2023/5/4 10:54
 */
@Configuration
public class CorsConfig {

    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        //可以跨域访问的服务地址
        configuration.addAllowedOrigin("http://localhost:8080");
        //设置是否可以发送cookie信息
        configuration.setAllowCredentials(true);
        //设置允许请求的方式
        List<String> allowedMethods = new ArrayList<>();
        allowedMethods.add("*");
        configuration.setAllowedMethods(allowedMethods);
        //设置允许的header
        List<String> allowedHeaders = new ArrayList<>();
        allowedHeaders.add("*");
        configuration.setAllowedHeaders(allowedHeaders);
        //匹配接口路径,什么接口路径要遵守这个规则,/** 表示所有路径都要遵守
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(corsSource);
    }
}
