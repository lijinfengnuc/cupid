/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.core.model;

import com.google.common.base.Strings;
import com.yx.cupid.core.constant.ApiResultCodeEnum;
import com.yx.cupid.core.util.DateTimeUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * ClassName: ApiResult <br/>
 * Function: <br/>
 * Date: 2020年08月20日 15:37 <br/>
 *
 * @author lijinfeng
 */

public class ApiResult<T> {

    /** Success code */
    private static final int SUCCESS_CODE = ApiResultCodeEnum.OK.value();
    /** Error message */
    private static final String DEFAULT_ERROR_MSG = "unknown error";



    /**
     * Build success result without data
     *
     * @return com.yx.cupid.core.model.ApiResult<java.lang.Void>
     */
    public static ApiResult<Void> ok() {
        return new ApiResult<>(SUCCESS_CODE);
    }

    /**
     * Build success result with data
     *
     * @param data data
     * @return com.yx.cupid.core.model.ApiResult<T>
     */
    public static <T> ApiResult<T> ok(@Nonnull T data) {
        return new ApiResult<>(SUCCESS_CODE, data);
    }

    /**
     * Build success result with data, data can be null
     *
     * @param data data
     * @return com.yx.cupid.core.model.ApiResult<T>
     */
    public static <T> ApiResult<?> okOrNull(@Nullable T data) {
        return null == data ? ok() : ok(data);
    }

    /**
     * Build error result
     *
     * @param code code
     * @param errMsg error message
     * @return com.yx.cupid.core.model.ApiResult<java.lang.Void>
     */
    public static ApiResult<Void> error(int code, @Nonnull String errMsg) {
        return new ApiResult<>(code, errMsg);
    }

    /**
     * Build error result, errMsg can be null
     *
     * @param code code
     * @param errMsg error message
     * @return com.yx.cupid.core.model.ApiResult<java.lang.Void>
     */
    public static ApiResult<Void> errorOrEmpty(int code, @Nullable String errMsg) {
        return error(code, Strings.isNullOrEmpty(errMsg) ? DEFAULT_ERROR_MSG : errMsg);
    }



    /** Code */
    private final int code;
    /** Error message */
    private String errMsg;
    /** Data */
    private T data;
    /** Timestamp */
    private final long timestamp;



    private ApiResult(int code){
        this.code = code;
        this.timestamp = DateTimeUtils.getLong(DateTimeUtils.getNow());
    }

    private ApiResult(int code, T data) {
        if(null == data) {
            throw new IllegalArgumentException("data can not be null");
        }
        this.code = code;
        this.data = data;
        this.timestamp = DateTimeUtils.getLong(DateTimeUtils.getNow());
    }

    private ApiResult(int code, String errMsg){
        if(Strings.isNullOrEmpty(errMsg)) {
            throw new IllegalArgumentException("msg can not be null or empty");
        }
        this.code = code;
        this.errMsg = errMsg;
        this.timestamp = DateTimeUtils.getLong(DateTimeUtils.getNow());
    }

    /**
     * Return {@link ApiResult#code}
     *
     * @return int
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Return {@link ApiResult#errMsg}
     *
     * @return java.lang.String
     */
    public String getErrMsg() {
        return this.errMsg;
    }

    /**
     * Return {@link ApiResult#errMsg} if not empty, else return default errMsg
     *
     * @param otherErrMsg default errMsg
     * @return java.lang.String
     */
    public String getErrMsgOrElse(String otherErrMsg) {
        return Optional.ofNullable(this.errMsg)
                .map(Strings::emptyToNull)
                .orElse(otherErrMsg);
    }

    /**
     * Return {@link ApiResult#data}
     *
     * @return T
     */
    public T getData() {
        return this.data;
    }

    /**
     * Return {@link ApiResult#data} if not null, else return default data
     *
     * @param otherData default data
     * @return T
     */
    public T getDataOrElse(T otherData) {
        return Optional.ofNullable(this.data).orElse(otherData);
    }

    /**
     * Return {@link ApiResult#timestamp}
     *
     * @return long
     */
    public long getTimestamp() {
        return timestamp;
    }

}
