/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

/**
 * ClassName: SecurityWebFilterChain <br/>
 * Function: <br/>
 * Date: 2020年08月14日 17:47 <br/>
 *
 * @author lijinfeng
 */

@Configuration
public class WebfluxSecurityConfig {

    /** Login URL */
    @Value("${security.login.url:/login}")
    private String loginUrl;
    /** Logout URL */
    @Value("${security.logout.url:/logout}")
    private String logoutUrl;
    /** Context path */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /** Custom {@link ReactiveAuthenticationManager} */
    private final ReactiveAuthenticationManager authenticationManager;
    /** Custom {@link ServerAuthenticationSuccessHandler} */
    private final ServerAuthenticationSuccessHandler authenticationSuccessHandler;
    /** Custom {@link ServerAuthenticationFailureHandler} */
    private final ServerAuthenticationFailureHandler authenticationFailureHandler;
    /** Custom {@link ServerLogoutSuccessHandler} */
    private final ServerLogoutSuccessHandler logoutSuccessHandler;
    /** Custom {@link ServerAuthenticationEntryPoint} */
    private final ServerAuthenticationEntryPoint authenticationEntryPoint;
    /** Custom {@link ServerAccessDeniedHandler} */
    private final ServerAccessDeniedHandler accessDeniedHandler;
    /** Custom {@link CorsConfigurationSource} */
    private final CorsConfigurationSource corsConfigurationSource;
    /** Custom {@link ServerAccessDeniedHandler} */
    private final ServerAccessDeniedHandler csrfAccessDeniedHandler;



    @Autowired
    public WebfluxSecurityConfig(ReactiveAuthenticationManager authenticationManager,
                                 ServerAuthenticationSuccessHandler authenticationSuccessHandler,
                                 ServerAuthenticationFailureHandler authenticationFailureHandler,
                                 ServerLogoutSuccessHandler logoutSuccessHandler,
                                 ServerAuthenticationEntryPoint authenticationEntryPoint,
                                 ServerAccessDeniedHandler accessDeniedHandler,
                                 CorsConfigurationSource corsConfigurationSource,
                                 ServerAccessDeniedHandler csrfAccessDeniedHandler) {
        this.authenticationManager = authenticationManager;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.corsConfigurationSource = corsConfigurationSource;
        this.csrfAccessDeniedHandler = csrfAccessDeniedHandler;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                /*URL permission management*/
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers(this.contextPath.concat(loginUrl), this.contextPath.concat("/test*")).permitAll()
                        .anyExchange().authenticated())
                /*set default authentication manager */
                .authenticationManager(this.authenticationManager)
                /*form login configuration*/
                .formLogin(formLoginSpec -> formLoginSpec
                        .loginPage(this.loginUrl)
                        .authenticationSuccessHandler(this.authenticationSuccessHandler)
                        .authenticationFailureHandler(this.authenticationFailureHandler))
                /*logout configuration*/
                .logout(logoutSpec -> logoutSpec
                        .logoutUrl(this.logoutUrl)
                        .logoutSuccessHandler(this.logoutSuccessHandler))
                /*exception handling configuration*/
                .exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec
                        .authenticationEntryPoint(this.authenticationEntryPoint)
                        .accessDeniedHandler(this.accessDeniedHandler))
                /*cors configuration*/
                .cors(corsSpec -> corsSpec
                        .configurationSource(this.corsConfigurationSource))
                /*csrf configuration*/
                .csrf(csrfSpec -> csrfSpec
                        .accessDeniedHandler(this.csrfAccessDeniedHandler));
        return http.build();
    }

}
