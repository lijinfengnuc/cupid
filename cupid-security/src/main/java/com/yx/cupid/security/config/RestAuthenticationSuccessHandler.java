/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.config;

import com.yx.cupid.core.model.ApiResult;
import com.yx.cupid.security.util.ServerWebExchangeUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ClassName: RestAuthenticationSuccessHandler <br/>
 * Function: <br/>
 * Date: 2020年08月19日 17:05 <br/>
 *
 * @author lijinfeng
 */

@Component
public class RestAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return ServerWebExchangeUtils.writeResponseInJson(webFilterExchange.getExchange().getResponse(),
                ApiResult::ok);
    }

}
