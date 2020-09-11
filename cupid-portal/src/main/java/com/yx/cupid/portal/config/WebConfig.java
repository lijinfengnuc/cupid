/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.portal.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * ClassName: WebConfig <br/>
 * Function: Configure WebFlux<br/>
 * Date: 2020年09月04日 18:04 <br/>
 *
 * @author lijinfeng
 */

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {

    /** {@link ObjectMapper} provider */
    private final ObjectMapperProvider objectMapperProvider;



    @Autowired
    public WebConfig(ObjectMapperProvider objectMapperProvider) {
        this.objectMapperProvider = objectMapperProvider;
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().jackson2JsonEncoder(
                new Jackson2JsonEncoder(objectMapperProvider.getObjectMapper(), MediaType.APPLICATION_JSON));
        configurer.defaultCodecs().jackson2JsonEncoder(
                new Jackson2JsonEncoder(objectMapperProvider.getObjectMapper(), MediaType.APPLICATION_JSON));
    }

}
