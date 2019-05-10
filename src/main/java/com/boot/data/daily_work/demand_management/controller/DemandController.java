package com.boot.data.daily_work.demand_management.controller;

import com.boot.data.daily_work.demand_management.entity.Demand;
import com.boot.data.daily_work.demand_management.repository.DemandRepository;
import com.boot.data.daily_work.demand_management.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98548
 * @create 2019-05-09 13:20
 * @description
 */
@RestController
@RequestMapping("/demand")
public class DemandController {

    @Autowired
    private DemandRepository demandRepository;

    @PostMapping("/add")
    public String add() {
        Demand demand = new Demand();
        demand.setDemandID(UUIDUtil.generateId());
        demandRepository.save(demand);
        return "ok";
    }

    @PostMapping("/update")
    public String upadte(Long id) {
        Demand demand = demandRepository.getOne(id);
        demand.setDataState(255);

        demandRepository.save(demand);
        return "ok";
    }
}
