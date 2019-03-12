package com.boot.data.dao;

import com.boot.data.entity.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by 98548 on 2019/3/13.
 */
public interface DutyRepository extends JpaRepository<Duty, String> {

    @Query(value = "SELECT * FROM t_duty WHERE (DutyStartDate >=?1 AND DutyEndDate <=?2) OR (DutyStartDate BETWEEN ?1 AND ?2) OR (DutyEndDate BETWEEN ?1 AND ?2) OR (?1 BETWEEN DutyStartDate AND DutyEndDate) OR (?2 BETWEEN DutyStartDate AND DutyEndDate)", nativeQuery = true)
    List<Duty> getDutiesByTimeZone(Date startDate, Date endDate);
}
