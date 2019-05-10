package com.boot.data.daily_work.demand_management.repository;

import com.boot.data.daily_work.demand_management.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 98548
 * @create 2019-05-09 13:23
 * @description
 */
public interface DemandRepository extends JpaRepository<Demand, Long> {

    @Query(value = "select ******* WHERE id =  ?1", nativeQuery = true)
    List<Demand> test(String id);
}
