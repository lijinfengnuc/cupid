/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.portal.handler;

import com.yx.cupid.core.constant.ApiResultCodeEnum;
import com.yx.cupid.core.model.ApiResult;
import com.yx.cupid.security.util.ServerWebExchangeUtils;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;

/**
 * ClassName: ErrorHandler <br/>
 * Function: Handle exceptions(eg:404) outside controller<br/>
 * Date: 2020年09月04日 11:42 <br/>
 *
 * @author lijinfeng
 */

@Component
@Order(0)
public class ErrorHandler implements ErrorWebExceptionHandler {

    @Nonnull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, @Nonnull Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        return ServerWebExchangeUtils.writeResponseInJson(response, () -> {
            int code = ApiResultCodeEnum.INTERNAL_SERVER_ERROR.value();
            if(ex instanceof ResponseStatusException) {
                code = ((ResponseStatusException) ex).getStatus().value();
            }
            return ApiResult.errorOrEmpty(code, ex.getMessage());
        });
    }

}
