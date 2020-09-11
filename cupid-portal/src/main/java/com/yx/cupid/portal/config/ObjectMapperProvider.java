/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.portal.config;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * InterfaceName: ObjectMapperProvider <br/>
 * Function: <br/>
 * Date: 2020年09月10日 10:52 <br/>
 *
 * @author lijinfeng
 */

public interface ObjectMapperProvider {

    /**
     * Provide custom {@link ObjectMapper}
     *
     * @return com.fasterxml.jackson.databind.ObjectMapper
     */
    ObjectMapper getObjectMapper();

}
