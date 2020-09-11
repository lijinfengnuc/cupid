/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.portal.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * ClassName: DefaultObjectMapperProvider <br/>
 * Function: <br/>
 * Date: 2020年09月10日 11:03 <br/>
 *
 * @author lijinfeng
 */

@Component
public class DefaultObjectMapperProvider implements ObjectMapperProvider{

    @Override
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

}
