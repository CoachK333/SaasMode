package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.dao.UserRepository;
import com.boot.data.entity.User;
import com.boot.data.util.DateUtils;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * @author 98548
 * @create 2019-02-19 15:06
 * @description
 */
@RestController
@RequestMapping("/user")
@Api("/用户查询")
public class TestController {

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/test003")
    public String test003(MultipartFile file) {
        List<VOExcelData> list = new ArrayList();

        try (InputStream inputStream = file.getInputStream()) {

            Workbook workbook = new HSSFWorkbook(inputStream);
            Sheet sheet001 = workbook.getSheetAt(0);
            int i0 = sheet001.getFirstRowNum();
            int iend = sheet001.getLastRowNum();

            System.out.println(i0);
            System.out.println(iend);

            Row row001 = sheet001.getRow(i0);
            short j0 = row001.getFirstCellNum();
            short jend = row001.getLastCellNum();
            System.out.println(j0);
            System.out.println(jend);

            String date_prefix = row001.getCell(0).getStringCellValue();
            String dayStr = row001.getCell(1).getStringCellValue();
            String yearStr = date_prefix.substring(0, 4);
            String monthStr = date_prefix.substring(4, 6);

            LocalDate startDate;


            Set<String> dutyTypes = new HashSet<>();
            for (int i = i0 + 2; i <= iend; i++) {
                Row row = sheet001.getRow(i);
                startDate = LocalDate.of(Integer.parseInt(yearStr), Integer.parseInt(monthStr), Integer.parseInt(dayStr));
                for (short j = (short) (j0 + 1); j < jend; j++) {
                    VOExcelData voExcelData = new VOExcelData();

                    voExcelData.setDate(DateUtils.localdateToDate(startDate));
                    voExcelData.setName(row.getCell(j0).getStringCellValue());
                    String content = row.getCell(j).getStringCellValue();
                    voExcelData.setContent(content);
                    dutyTypes.add(content);
                    list.add(voExcelData);
                    startDate = startDate.plusDays(1);
                }
            }
            System.out.println(JSON.toJSONString(list));
            System.out.println(dutyTypes);
            for (VOExcelData voExcelData : list) {
                String content = voExcelData.getContent();


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @GetMapping("/test004")
    public String test004(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.list(pageable);
        System.out.println(users.getTotalElements());
        System.out.println(users.getNumberOfElements());
        System.out.println(users.getNumber());
        System.out.println(users.getTotalPages());
        System.out.println(users.getSize());
        return JSON.toJSONString(users.getContent());
    }

}
