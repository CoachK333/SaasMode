package com.boot.data.dutyscheduling.approval.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-02-14 10:22
 * @description
 */
@Data
@Entity
@Table(name = "t_duty_scheduling_approval_log")
public class DutySchedulingApprovalLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT  COMMENT '排班审批日志id'")
    private Long id;

    @Column(name = "duty_scheduling_id", columnDefinition = "BIGINT  COMMENT '排班id'")
    private Long duty_scheduling_id;

    @Column(name = "comments", columnDefinition = "VARCHAR(255) COMMENT '审批意见'")
    private String comments;

    @Column(name = "description", columnDefinition = "VARCHAR(255) COMMENT '描述'")
    private String description;

    @Column(name = "approval_state", columnDefinition = "tinyint(3) COMMENT '审批结果(1:同意;2:驳回)'")
    private Integer approvalState;

    @Column(name = "file_id", columnDefinition = "VARCHAR(255) COMMENT '附件id'")
    private String fileId;

    @Column(name = "request_user_id", columnDefinition = "BIGINT COMMENT '申请人'")
    private Long request_user_id;

    @Column(name = "approver_user_id", columnDefinition = "BIGINT COMMENT '审批人'")
    private Long approver_user_id;

    @Column(name = "approval_date", columnDefinition = "datetime COMMENT '审批日期'")
    private Date approvalDate;

    @Column(name = "createDate", columnDefinition = "datetime COMMENT '创建日期'")
    private Date createDate;


}
