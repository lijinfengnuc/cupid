/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.core.constant;

/**
 * EnumName: ApiResultCodeEnum <br/>
 * Function: <br/>
 * Date: 2020年08月21日 10:05 <br/>
 *
 * @author lijinfeng
 */

public enum ApiResultCodeEnum {

    /** Success */
    OK(200),
    /** Illegal argument */
    BAD_REQUEST(400),
    /** Authentication failed*/
    UNAUTHORIZED(401),
    /** Access denied */
    FORBIDDEN(403),
    /** Internal server error */
    INTERNAL_SERVER_ERROR(500);



    /** The value is used for {@link com.yx.cupid.core.model.ApiResult} as code field*/
    private final int value;



    /**
     * Constructor with {@link ApiResultCodeEnum#value}
     *
     * @param value {@link ApiResultCodeEnum#value}
     */
    ApiResultCodeEnum(int value) {
        this.value = value;
    }

    /**
     * Return {@link ApiResultCodeEnum#value}
     *
     * @return int
     */
    public int value() {
        return this.value;
    }

}
