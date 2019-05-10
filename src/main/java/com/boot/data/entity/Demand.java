package com.boot.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-04-30 9:26
 * @description
 */
//@Entity
//@Table(name = "t_demand")//原表+日志表
public class Demand {
    private Long id;

    private String title;

    private String content;

    private String supplement;//补录


    private Long createUserID;
    private String createUser;
    private Date createDate;

    private Long updateUserID;
    private String updateUser;
    private Date updateDate;

    private Long handlerID;

    private String handlerName;

    private String createMobile;//来电电话

    private Long createDepartmentID;

    private String createDepartment;

    private String attachmentIDs;//需求附件(多个)

    private Long itilServiceID;
    //关联字段:1.服务目录;2.会议;3.项目
    private Long preBusinessItilServiceID;
    private Long finalBusinessItilServiceID;
    private Long preSupportItilServiceID;
    private Long finalSupportItilServiceID;

    private Long receptionCallLogID;//;来电日志

    private Long meetingID;

    private Long projectID;

    private Integer channel;//(1.微信;2.钉钉;3.PC)

    private Integer dataState;
}
