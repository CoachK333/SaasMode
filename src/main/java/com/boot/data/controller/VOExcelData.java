package com.boot.data.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-03-05 10:21
 * @description 目的是用作排班审批文件展示, 也可用作Excel通用数据
 */
@Data
@Component
public class VOExcelData implements Serializable {

    private static final long serialVersionUID = -3284683327279017631L;
    //姓名    行标
    private String name;
    //日期    列标
    @JSONField(format = "yyyyMMdd")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date date;
    //值班类型  内容
    private String content;
}
