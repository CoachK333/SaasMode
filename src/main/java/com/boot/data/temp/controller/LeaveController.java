package com.boot.data.temp.controller;

import com.boot.data.Vo.DateVo;
import com.boot.data.temp.entity.Leave;
import com.boot.data.temp.service.LeaveService;
import com.boot.data.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 98548
 * @create 2019-04-12 17:27
 * @description
 */
@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/list")
    public List<Leave> list(String startDateStr, String endDateStr) {
        DateVo dateVo = new DateVo(startDateStr, endDateStr);
        DateUtils.autoFillTimeStr(dateVo);

        List<Leave> leaves = leaveService.list(dateVo.getStartDateStr(), dateVo.getEndDateStr());
        System.out.println(leaves);
        return leaves;
    }

}
