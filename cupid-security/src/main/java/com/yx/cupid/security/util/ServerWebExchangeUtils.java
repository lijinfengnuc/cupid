/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.util;

import com.google.common.base.Charsets;
import com.yx.cupid.core.gson.GsonHolder;
import com.yx.cupid.core.model.ApiResult;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

/**
 * ClassName: ServerWebExchangeUtils <br/>
 * Function: <br/>
 * Date: 2020年09月07日 10:51 <br/>
 *
 * @author lijinfeng
 */

public class ServerWebExchangeUtils {

    /**
     * Write {@link ApiResult} to {@link ServerHttpResponse} in json
     *
     * @param response server http response
     * @param apiResultSupplier provide {@link ApiResult}
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    public static Mono<Void> writeResponseInJson(ServerHttpResponse response,
                                                 Supplier<ApiResult<?>> apiResultSupplier) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> buildDataBufferInJson(response, apiResultSupplier.get())));
    }

    /**
     * Use {@link ApiResult} to build {@link DataBuffer} in json
     *
     * @param response server http response
     * @param apiResult result to response
     * @return org.springframework.core.io.buffer.DataBuffer
     */
    public static DataBuffer buildDataBufferInJson(ServerHttpResponse response, ApiResult<?> apiResult) {
        return response.bufferFactory()
                .wrap(GsonHolder.getCommonGson().toJson(apiResult).getBytes(Charsets.UTF_8));
    }

}
