package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.dao.DutyRepository;
import com.boot.data.dao.UserRepository;
import com.boot.data.entity.Duty;
import com.boot.data.entity.User;
import com.boot.data.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 98548
 * @create 2019-02-19 15:06
 * @description
 */
@RestController
@RequestMapping("/test")
@Api("/测试接口")
public class TestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DutyRepository dutyRepository;

    @PostMapping("/add")
    public String addOne(String code, String name) {
        User user = new User();
        user.setName(name);
        user.setDesc(code);
        userRepository.save(user);
        return "ok";
    }

    @GetMapping("/getAll")
    public String getAll() {
        List<User> users = userRepository.findAll();
        return JSON.toJSONString(users);
    }

    @GetMapping("/test001")
    public String test001() {
        return JSON.toJSONString(userRepository.getAll());
    }

    @GetMapping("/test002")
    public String test002(String name) {
        Integer exo = userRepository.exo(name);
        return JSON.toJSONString(exo);
    }

    @PostMapping("/zbDetails")
    @ApiOperation("值班情况展示--按照(周/组别)展示值班情况")
    public String zbDetails(@ApiParam("年和月份（yyyyMMdd）开始") @RequestParam String start,
                            @ApiParam("年和月份（yyyyMMdd）结束") @RequestParam String end,
                            @ApiParam("类型ID 可不传（所有类型）") @RequestParam(required = false, defaultValue = "") String type) throws ParseException {
        if (org.apache.commons.lang3.StringUtils.isAnyBlank(start, end)) {
            return new String("参数为空!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date st, en;

        st = sdf.parse(start);
        en = sdf.parse(end);

        return sdf.format(st) + sdf.format(en);
    }

    @GetMapping("/test003")
    public List<Duty> test003(String start, String end) throws ParseException {

        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyyMMdd"));

        LocalDateTime startTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(endDate, LocalTime.MAX);

        List<Duty> duties = dutyRepository.getDutiesByTimeZone(DateUtils.localDateTime2Date(startTime), DateUtils.localDateTime2Date(endTime));
        Map<LocalDate, List<Duty>> map = new HashMap<>();
        for (Duty duty : duties) {
            Date dutyStartDate0 = duty.getDutyStartDate();
            LocalDate localDate = DateUtils.date2LocalDate(dutyStartDate0);
            if (!map.containsKey(localDate)) {
                List<Duty> dutyList = new ArrayList<>();
                dutyList.add(duty);
                map.put(localDate, dutyList);
            } else {
                List<Duty> dutyList = map.get(localDate);
                dutyList.add(duty);
            }
        }
        return duties;
    }


    @PutMapping("/test004")
    public String test004(Long id) {
        User one = userRepository.getOne(id);
        one.setDesc("hahah");

        User user = new User();
        user.setName("zhangxiaosan");
        user.setCreateDate(DateUtils.localDateTime2Date(LocalDateTime.of(LocalDate.now(), LocalTime.MAX)));

        List<User> users = new ArrayList<>();
        users.add(one);
        users.add(user);
        userRepository.saveAll(users);
        return "ok";
    }

}
