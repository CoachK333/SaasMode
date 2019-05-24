package com.boot.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 98548
 * @create 2019-05-23 18:08
 * @description 用户通知表
 */
@Data
@Entity
@Table(name = "t_user_notification")
@IdClass(UserNotification.UserNotifKeys.class)
public class UserNotification {

    @Id
    @Column(name = "user_id")
    private Long userID;        //用户id
    @Id
    @Column(name = "business_id")
    private Long businessID;    //业务id

    @Column(name = "business_type")
    private Integer businessType;   //业务类型 (1:demand,)

    @Column(name = "notification_flag")
    private boolean notificationFlag;   //通知标识

    @Column(name = "data_state")
    private Integer dataState;          //数据有效性标识

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class UserNotifKeys implements Serializable {

        private static final long serialVersionUID = 762438874990066128L;

        private Long userID;
        private Long businessID;
    }
}
