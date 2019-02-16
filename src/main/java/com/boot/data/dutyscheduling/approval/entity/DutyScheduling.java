package com.boot.data.dutyscheduling.approval.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-01-25 10:57
 * @description 值班排班(审批用)
 */
@Data
@Entity
@Table(name = "t_duty_scheduling")
public class DutyScheduling implements Serializable {
    //排班id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT  COMMENT '排班id'")
    private Long id;
    //排班起始日期
    @Column(name = "scheduling_start_date", columnDefinition = "datetime COMMENT '排班起始日期'")
    private Date scheduling_startDate;
    //排班结束日期
    @Column(name = "scheduling_end_date", columnDefinition = "datetime COMMENT '排班结束日期'")
    private Date scheduling_endDate;
    //审批人
    @Column(name = "approver_user_id", columnDefinition = "BIGINT COMMENT '审批人'")
    private Long approver_user_id;
    //申请人
    @Column(name = "request_user_id", columnDefinition = "BIGINT COMMENT '申请人'")
    private Long request_user_id;
    //附件id
    @Column(name = "file_id", columnDefinition = "VARCHAR(255) COMMENT '附件id'")
    private String fileId;
    //描述
    @Column(name = "description", columnDefinition = "VARCHAR(255) COMMENT '描述'")
    private String description;
    //审批意见
    @Column(name = "comments", columnDefinition = "VARCHAR(255) COMMENT '审批意见'")
    private String comments;
    //审批状态
    @Column(name = "approval_state", columnDefinition = "tinyint(3) COMMENT '审批状态(0:审核中;1:审核成功;2:审核驳回)'")
    private Integer approvalState;
    //创建日期
    @Column(name = "createDate", columnDefinition = "datetime COMMENT '创建日期'")
    private Date createDate;
    //最近修改日期
    @Column(name = "updateDate", columnDefinition = "datetime COMMENT '最近修改日期'")
    private Date updateDate;
}
