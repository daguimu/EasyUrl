/*
 * Copyright (c) 2024 ly.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Guimu
 * @date 2024/09/23
 */
public class DateUtils {
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }
}