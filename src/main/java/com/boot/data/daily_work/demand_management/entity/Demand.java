package com.boot.data.daily_work.demand_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-05-09 9:45
 * @description
 */
@Entity
@Table(name = "t_demand")
@Data
public class Demand {
    @Id
    @Column(name = "demand_id", columnDefinition = "BIGINT COMMENT '需求id'")
    private Long demandID;
    @Column(name = "demand_no", columnDefinition = "VARCHAR(64) COMMENT '审批人'")
    private String demandNO;
    @Column(name = "request_person", columnDefinition = "VARCHAR(50) COMMENT '申告人'")
    private String requestPerson;
    @Column(name = "request_person_id", columnDefinition = "BIGINT COMMENT '申告人ID'")
    private Long requestPersonID;
    @Column(name = "request_tel_no", columnDefinition = "VARCHAR(50) COMMENT '来电号码'")
    private String requestTelNo;
    @Column(name = "request_source", columnDefinition = "TINYINT(4) unsigned COMMENT '需求来源'")
    private Integer requestSource;
    @Column(name = "business_itil_service_id", columnDefinition = "BIGINT COMMENT '服务目录ID'")
    private Long businessItilServiceID;
    @Column(name = "department_id", columnDefinition = "BIGINT COMMENT '部门ID(申请部门)'")
    private Long departmentID;
    @Column(name = "department_address", columnDefinition = "VARCHAR(200) COMMENT '部门地址'")
    private String departmentAddress;
    @Column(name = "department_tel_no", columnDefinition = "VARCHAR(50) COMMENT '部门电话'")
    private String departmentTelNo;
    @Column(name = "calllog_id", columnDefinition = "BIGINT COMMENT '来电ID'")
    private Long callLogID;
    @Column(name = "demand_title", columnDefinition = "VARCHAR(500) COMMENT '需求标题'")
    private String demandTitle;
    @Column(name = "demand_description", columnDefinition = "LONGTEXT COMMENT '需求描述'")
    private String demandDescription;//todo
    @Column(name = "supplement_count", columnDefinition = "INT(11) COMMENT '需求描述'")
    private Long supplementCount;
    @Column(name = "demand_count", columnDefinition = "INT(11) COMMENT '所有的我也要提交此 需求和补录此需求的 总数'")
    private Long demandCount;
    @Column(name = "project_id", columnDefinition = "BIGINT COMMENT '项目ID'")
    private Long projectID;
    @Column(name = "meeting_id", columnDefinition = "BIGINT COMMENT '会议ID'")
    private Long meetingID;
    @Column(name = "incident_id", columnDefinition = "BIGINT COMMENT '事件ID'")
    private Long incidentID;
    @Column(name = "priority_type", columnDefinition = "TINYINT(4) unsigned COMMENT '优先级'")
    private Integer priorityType;
    @Column(name = "importance_level", columnDefinition = "TINYINT(4) unsigned COMMENT '重要级别'")
    private Integer importanceLevel;
    @Column(name = "current_user_id", columnDefinition = "BIGINT COMMENT '当前处理人'")
    private Long currentUserID;
    @Column(name = "current_state", columnDefinition = "TINYINT(4) unsigned COMMENT '当前状态'")
    private Integer currentState;
    @Column(name = "order_index", columnDefinition = "INT(11) COMMENT '排序'")
    private Long orderIndex;
    @Column(name = "create_user_id", columnDefinition = "BIGINT COMMENT '创建人ID'")
    private Long createUserID;
    @Column(name = "create_user", columnDefinition = "VARCHAR(50) COMMENT '创建人'")
    private String createUser;
    @Column(name = "create_date", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createDate;
    @Column(name = "update_user_id", columnDefinition = "BIGINT COMMENT '修改人ID'")
    private Long updateUserID;
    @Column(name = "update_user", columnDefinition = "VARCHAR(50) COMMENT '修改人'")
    private String updateUser;
    @Column(name = "update_date", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Date updateDate;
    @Column(name = "data_state", columnDefinition = "TINYINT(4) unsigned COMMENT '数据状态(1:有效,255删除)'")
    private Integer dataState;
}
