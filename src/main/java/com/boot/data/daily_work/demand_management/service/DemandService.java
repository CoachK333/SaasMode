package com.boot.data.daily_work.demand_management.service;

import com.boot.data.daily_work.demand_management.entity.Demand;

import java.util.List;

/**
 * @author 98548
 * @create 2019-05-09 16:13
 * @description
 */
public interface DemandService {
    //查找刘表
    List<Demand> findAll();
}
