package com.boot.data.util;

import java.text.DecimalFormat;

/**
 * @author 98548
 * @create 2019-04-10 14:36
 * @description
 */
public class NumberUtils {
    /**
     * 保留两位小数(i1/i2)
     *
     * @param i1
     * @param i2
     * @return
     */
    public String division(Integer i1, Integer i2) {
        DecimalFormat format = new DecimalFormat("0.00");
        float v = (float) i1 / i2;
        return String.valueOf(v);
    }

}
