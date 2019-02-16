package com.boot.data.dutyscheduling.approval.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.dutyscheduling.approval.entity.DutyScheduling;
import com.boot.data.dutyscheduling.approval.service.DutySchedulingService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 98548
 * @create 2019-01-30 16:18
 * @description 值班排班
 */
@RequestMapping("/dutyScheduling")
@RestController
public class DutySchedulingController {

    @Autowired
    private DutySchedulingService dutySchedulingService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    //添加/修改
    @PostMapping("/insertOrUpdate")
    public String insertOrUpdate(String startDateStr,
                                 String endDateStr,
                                 String approverUid,
                                 MultipartFile file,
                                 String comment,
                                 Long id) throws Exception {

        if (StringUtils.isAnyBlank(startDateStr, endDateStr, approverUid, comment) || !ObjectUtils.allNotNull(file)) {
            return "入参不能为空";
        }
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);
        DutyScheduling dutyScheduling = new DutyScheduling();
        if (id != null) {
            dutyScheduling.setId(id);
        }
        dutyScheduling.setScheduling_startDate(startDate);
        dutyScheduling.setScheduling_endDate(endDate);
        //todo -- tomodify 文件上传之后会有fileID;
        dutyScheduling.setFileId("123123123123");
        dutyScheduling.setApprover_user_id(Long.valueOf(approverUid));
        dutyScheduling.setDescription(comment);
        dutyScheduling.setCreateDate(new Date());
        dutyScheduling.setUpdateDate(new Date());

        dutySchedulingService.updateOrInsert(dutyScheduling);
        return "ok";

    }

    //获取所有
    @GetMapping("/getAll")
    public String getAll() {
        List list = dutySchedulingService.getAll();
        return JSON.toJSONString(list);
    }

    //审批
    @PostMapping("/approval")
    public String approval(long dutySchedulingId, String comments, int state) {
        if (!ObjectUtils.allNotNull(dutySchedulingId, state)) {
            return "request parameters cannot be null";
        }
        dutySchedulingService.approval(dutySchedulingId, comments, state);
        return "ok";
    }

}
