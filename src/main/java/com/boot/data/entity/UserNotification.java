package com.boot.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 98548
 * @create 2019-05-23 18:08
 * @description 用户通知表
 */
@Data
@Entity
@Table(name = "t_user_notification")
@IdClass(UserNotifKeys.class)
public class UserNotification {

    @Id
    @Column(name = "user_id", columnDefinition = "BIGINT COMMENT '用户id'")
    private Long userID;        //用户id
    @Id
    @Column(name = "business_id", columnDefinition = "BIGINT COMMENT '业务id'")
    private Long businessID;    //业务id

    @Column(name = "business_type", columnDefinition = "TINYINT(4) unsigned COMMENT '业务类型(比如,1:demand)'")
    private Integer businessType;   //业务类型 (1:demand,)

    @Column(name = "notification_flag", columnDefinition = "bit(1) DEFAULT 0 COMMENT'通知标识(true/false)'")
    private Boolean notificationFlag;   //通知标识

    @Column(name = "data_state", columnDefinition = "TINYINT(4) unsigned COMMENT '数据状态(1:正常使用;255:删除)'")
    private Integer dataState;          //数据有效性标识
}
