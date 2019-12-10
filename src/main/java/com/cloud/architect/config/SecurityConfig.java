package com.cloud.architect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Cao Yuliang on 2019-12-10.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        // Spring Security 默认开启了 CSRF 的保护，H2Database 相关的请求需要携带 CSRF Token 及相关参数，所以访问时候出现了 403 。
        http.csrf().disable();
        // Spring Security 默认页面不允许 iframe （不安全）
        http.headers().frameOptions().disable();
    }
}
