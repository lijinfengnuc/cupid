/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.portal.handler;

import com.yx.cupid.core.model.ApiResult;
import com.yx.cupid.security.util.ServerWebExchangeUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.HandlerResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ClassName: ResultHandler <br/>
 * Function: <br/>
 * Date: 2020年09月09日 13:21 <br/>
 *
 * @author lijinfeng
 */

@Component
@Order(0)
public class ResultHandler implements HandlerResultHandler {

    @Override
    public boolean supports(HandlerResult result) {
        MethodParameter returnTypeSource = result.getReturnTypeSource();
        Method method = returnTypeSource.getMethod();
        if(null == method) {
            return false;
        }
        Annotation[] methodAnnotations = method.getAnnotations();
        /*whether method has annotation @ResponseBody*/
        if(0 != methodAnnotations.length &&
                Arrays.stream(methodAnnotations).anyMatch(annotation -> annotation instanceof ResponseBody)) {
            return true;
        }else {
            /*whether class has annotation @RestController*/
            Annotation[] classAnnotations = method.getDeclaringClass().getAnnotations();
            return 0 != classAnnotations.length &&
                    Arrays.stream(classAnnotations).anyMatch(annotation -> annotation instanceof RestController);
        }
    }

    @Nonnull
    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        Object returnValue = result.getReturnValue();
        ServerHttpResponse response = exchange.getResponse();
        if(null == returnValue) {
            return ServerWebExchangeUtils.writeResponseInJson(response, ApiResult::ok);
        }
        if(returnValue instanceof Mono) {
            /*handle Mono*/
            return response.writeWith(((Mono<?>) returnValue)
                    .map(data -> ServerWebExchangeUtils.buildDataBufferInJson(response, ApiResult.ok(data)))
                    .switchIfEmpty(Mono.fromSupplier(
                            () -> ServerWebExchangeUtils.buildDataBufferInJson(response, ApiResult.ok()))));
        } else {
            /*handle Flux*/
            return response.writeWith(((Flux<?>) returnValue)
                    .map(data -> ServerWebExchangeUtils.buildDataBufferInJson(response, ApiResult.ok(data)))
                    .switchIfEmpty(Mono.fromSupplier(
                            () -> ServerWebExchangeUtils.buildDataBufferInJson(response, ApiResult.ok()))));
        }
    }

}
