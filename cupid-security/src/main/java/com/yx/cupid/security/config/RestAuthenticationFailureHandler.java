/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.config;

import com.yx.cupid.core.constant.ApiResultCodeEnum;
import com.yx.cupid.core.model.ApiResult;
import com.yx.cupid.security.util.ServerWebExchangeUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ClassName: RestAuthenticationFailureHandler <br/>
 * Function: <br/>
 * Date: 2020年08月19日 17:22 <br/>
 *
 * @author lijinfeng
 */

@Component
public class RestAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        return ServerWebExchangeUtils.writeResponseInJson(webFilterExchange.getExchange().getResponse(),
                () -> ApiResult.errorOrEmpty(ApiResultCodeEnum.UNAUTHORIZED.value(), exception.getMessage()));
    }

}
