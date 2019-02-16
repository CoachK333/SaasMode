package com.boot.data.dutyscheduling.approval.repository;

import com.boot.data.dutyscheduling.approval.entity.DutyScheduling;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 98548 on 2019/1/30.
 */
public interface DutySchedulingRepository extends JpaRepository<DutyScheduling, Long> {
}
