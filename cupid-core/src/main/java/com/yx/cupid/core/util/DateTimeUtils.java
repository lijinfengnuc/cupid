/*
 * Copyright (c) 1993, 2020, the original author or authors. All rights reserved.
 */

package com.yx.cupid.core.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * ClassName: DateTimeUtils <br/>
 * Function: <br/>
 * Date: 2020年09月02日 15:15 <br/>
 *
 * @author lijinfeng
 */

public class DateTimeUtils {

    /**
     * Return the current {@link LocalDateTime} from the system clock in the default time-zone
     *
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    /**
     * Convert {@link LocalDateTime} to millisecond timestamp
     *
     * @param localDateTime {@link LocalDateTime}
     * @return long
     */
    public static long getLong(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

}
