package com.boot.data.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author 98548
 * @create 2019-05-16 15:21
 * @description
 */
public class CommonUtils {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public String orderNum() {
        return dateTimeFormatter.format(LocalDateTime.now()) + new Random().nextInt(1);
    }
}
