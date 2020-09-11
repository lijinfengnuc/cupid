/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.config;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.util.List;

/**
 * ClassName: CustomCorsConfigurationSource <br/>
 * Function: <br/>
 * Date: 2020年08月20日 10:30 <br/>
 *
 * @author lijinfeng
 */

@Component
public class CustomCorsConfigurationSource implements CorsConfigurationSource {

    /** Cors allowed headers */
    private static final List<String> ALLOWED_HEADERS = List.of("*");
    /** Cors allowed methods */
    private static final List<String> ALLOWED_METHODS = List.of(HttpMethod.GET.name());
    /** Cors allowed origins */
    private static final List<String> ALLOWED_ORIGINS = List.of("*");
    /** Cors max age */
    private static final Duration MAX_AGE = Duration.ofDays(3);



    @Override
    public CorsConfiguration getCorsConfiguration(@Nonnull ServerWebExchange exchange) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(ALLOWED_HEADERS);
        corsConfiguration.setAllowedMethods(ALLOWED_METHODS);
        corsConfiguration.setAllowedOrigins(ALLOWED_ORIGINS);
        corsConfiguration.setMaxAge(MAX_AGE);
        return corsConfiguration;
    }

}
