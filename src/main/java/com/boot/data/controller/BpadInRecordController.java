package com.boot.data.controller;

import com.boot.data.dao.BranchPadInputRecordRepository;
import com.boot.data.entity.BranchPadInputRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 98548
 * @create 2019-08-23 9:18
 * @description
 */
@RestController
@RequestMapping("/bpadInRecord")
@Slf4j
public class BpadInRecordController {

    @Autowired
    private BranchPadInputRecordRepository inputRecordRepository;

    @GetMapping("/getAll")
    public List<BranchPadInputRecord> getAll() {
        return inputRecordRepository.findAll();
    }

    @PutMapping("/insert")
    public String insert(@RequestBody BranchPadInputRecord record) {
        try {
            inputRecordRepository.save(record);
            return "true";
        } catch (Exception e) {
            log.error("保存失败{}", e);
            return "error";
        }
    }

    @PostMapping("/saveAll")
    public String saveAll(@RequestBody List<BranchPadInputRecord> records) {
        try {
            List<BranchPadInputRecord> inputRecords = inputRecordRepository.saveAll(records);
            return "true";
        } catch (Exception e) {
            log.error("保存失败{}", e);
            return "error";
        }
    }
}
