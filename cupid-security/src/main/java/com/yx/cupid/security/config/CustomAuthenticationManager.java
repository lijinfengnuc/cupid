/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * ClassName: CustomAuthenticationManager <br/>
 * Function: <br/>
 * Date: 2020年08月20日 10:19 <br/>
 *
 * @author lijinfeng
 */

@Component
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {

    /** Custom authenticationManager */
    private final ReactiveAuthenticationManager authenticationManager;



    @Autowired
    public CustomAuthenticationManager(ReactiveUserDetailsService userDetailsService) {
        authenticationManager =  new DelegatingReactiveAuthenticationManager(
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService));
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return authenticationManager.authenticate(authentication);
    }

}
