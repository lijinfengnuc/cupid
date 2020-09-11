/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.portal.rest;

import com.yx.cupid.core.util.DateTimeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * ClassName: Login <br/>
 * Function: <br/>
 * Date: 2020年09月04日 17:39 <br/>
 *
 * @author lijinfeng
 */

@RestController
public class Login {

    @GetMapping("/login")
    public Mono<String> handleLogin() {
        System.out.println("start" + DateTimeUtils.getNow());
        Mono<String> result = Mono.fromSupplier(() -> {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "login";
        });
        System.out.println("end" + DateTimeUtils.getNow());
        return result;
    }

    @GetMapping("/test")
    public Mono<String> test() {
        System.out.println("start" + DateTimeUtils.getNow());
        Mono<String> result = Mono.create(monoSink -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monoSink.success("test");
            System.out.println("end" + DateTimeUtils.getNow());
        });
        System.out.println("end" + DateTimeUtils.getNow());
        return result;
    }

    @GetMapping("/test1")
    public Mono<String> test1() {
        System.out.println("start" + DateTimeUtils.getNow());
        Mono<String> result = Mono.just(getValue());
        System.out.println("end" + DateTimeUtils.getNow());
        return result;
    }

    private String getValue() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test1";
    }

}
