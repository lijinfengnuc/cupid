/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.portal.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * ClassName: ContextPathFilter <br/>
 * Function: Make contextPath effective<br/>
 * Date: 2020年09月04日 17:59 <br/>
 *
 * @author lijinfeng
 */

@Component
@Order(0)
public class ContextPathFilter implements WebFilter {

    /** Context path */
    @Value("${server.servlet.context-path:/cupid/portal}")
    private String contextPath;



    @NonNull
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (request.getURI().getPath().startsWith(contextPath)) {
            return chain.filter(exchange
                    .mutate()
                    .request(request.mutate().contextPath(contextPath).build())
                    .build()
            );
        }
        return chain.filter(exchange);
    }

}
