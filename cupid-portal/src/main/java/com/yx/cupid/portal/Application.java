/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: Application <br/>
 * Function: <br/>
 * Date: 2020年08月04日 17:18 <br/>
 *
 * @author lijinfeng
 */

@SpringBootApplication(scanBasePackages = "com.yx.cupid")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
