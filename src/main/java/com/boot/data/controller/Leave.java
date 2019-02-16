package com.boot.data.controller;

import lombok.Data;
import org.apache.poi.hssf.record.SSTRecord;

import java.util.List;
import java.util.Map;

/**
 * @author 98548
 * @create 2019-01-04 14:57
 * @description
 */
@Data
public class Leave {

    private String name;
    private List<Map<String, String>> am;
    private List<Map<String, String>> pm;

}
