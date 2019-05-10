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
//@Table(name = "t_demand_relation")//关联/+1 使用
public class DemandRelation {
    private Long id;

    private Long demandID;

    private String content;

    private Long createUserID;
    private String createUser;
    private Date createDate;

    private Long updateUserID;
    private String updateUser;
    private Date updateDate;

    private Long createDepartmentID;

    private String createDepartment;

    private String attachmentIDs;//需求附件(多个)

    private Long itilServiceID;
    //关联字段:1.服务目录;2.会议;3.项目
    private Long preBusinessItilServiceID;
    private Long finalBusinessItilServiceID;
    private Long preSupportItilServiceID;
    private Long finalSupportItilServiceID;

    private Long meetingID;

    private Long projectID;

    private Integer channel;//(1.微信;2.钉钉;3.PC)

    private Integer dataState;
}
