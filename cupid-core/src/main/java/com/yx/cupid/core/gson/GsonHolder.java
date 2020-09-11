/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.core.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ClassName: GsonHolder <br/>
 * Function: <br/>
 * Date: 2020年09月02日 14:57 <br/>
 *
 * @author lijinfeng
 */

public class GsonHolder {

    /** Common Gson instance */
    private static final Gson COMMON_GSON = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();



    /**
     * Return {@link GsonHolder#COMMON_GSON}
     *
     * @return com.google.gson.Gson
     */
    public static Gson getCommonGson() {
        return COMMON_GSON;
    }

}
