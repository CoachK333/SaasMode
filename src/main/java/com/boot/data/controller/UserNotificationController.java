package com.boot.data.controller;

import com.boot.data.dto.Result;
import com.boot.data.service.UserNotificationService;
import com.boot.data.toolbox.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98548
 * @create 2019-05-27 10:49
 * @description 用户通知接口
 */
@RestController
@RequestMapping("/userNotifi")
public class UserNotificationController {

    @Autowired
    private UserNotificationService userNotificationService;

    @PutMapping("/add")
    public Result add() {
        userNotificationService.add();
        return new ResultUtil<>().setSuccessMsg("ok");
    }
}
