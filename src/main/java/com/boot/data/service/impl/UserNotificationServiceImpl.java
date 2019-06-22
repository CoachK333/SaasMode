package com.boot.data.service.impl;

import com.boot.data.dao.UserNotificationRepository;
import com.boot.data.entity.UserNotification;
import com.boot.data.service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 98548
 * @create 2019-05-27 10:51
 * @description
 */
@Service
public class UserNotificationServiceImpl implements UserNotificationService {
    @Autowired
    private UserNotificationRepository userNotificationRepository;


    @Override
    public void add() {

        UserNotification userNotification = new UserNotification();
        userNotification.setUserID(0L);
        userNotification.setBusinessID(12312312312L);
        userNotification.setBusinessType(1);    //需求
        userNotification.setDataState(1);


        userNotificationRepository.save(userNotification);
    }
}
