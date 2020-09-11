/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.config;

import com.yx.cupid.core.constant.ApiResultCodeEnum;
import com.yx.cupid.core.model.ApiResult;
import com.yx.cupid.security.util.ServerWebExchangeUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: RestAuthenticationEntryPoint <br/>
 * Function: <br/>
 * Date: 2020年08月20日 10:19 <br/>
 *
 * @author lijinfeng
 */

@Component
public class RestAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return ServerWebExchangeUtils.writeResponseInJson(exchange.getResponse(),
                () -> ApiResult.errorOrEmpty(ApiResultCodeEnum.UNAUTHORIZED.value(), e.getMessage()));
    }

}
