/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;

/**
 * ClassName: SecurityWebFilterChain <br/>
 * Function: <br/>
 * Date: 2020年08月14日 17:47 <br/>
 *
 * @author lijinfeng
 */

public class SecurityWebFilterChain {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return null;
    }

}
