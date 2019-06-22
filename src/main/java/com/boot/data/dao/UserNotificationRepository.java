package com.boot.data.dao;

import com.boot.data.entity.UserNotifKeys;
import com.boot.data.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 98548 on 2019/5/27.
 */
public interface UserNotificationRepository extends JpaRepository<UserNotification, UserNotifKeys> {
}
