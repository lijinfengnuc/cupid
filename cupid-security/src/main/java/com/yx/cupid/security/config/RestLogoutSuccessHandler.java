/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.config;

import com.yx.cupid.core.model.ApiResult;
import com.yx.cupid.security.util.ServerWebExchangeUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ClassName: RestLogoutSuccessHandler <br/>
 * Function: <br/>
 * Date: 2020年08月19日 16:51 <br/>
 *
 * @author lijinfeng
 */

@Component
public class RestLogoutSuccessHandler implements ServerLogoutSuccessHandler {

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        return ServerWebExchangeUtils.writeResponseInJson(exchange.getExchange().getResponse(), ApiResult::ok);
    }

}
