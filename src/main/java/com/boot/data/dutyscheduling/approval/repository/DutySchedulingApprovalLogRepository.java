package com.boot.data.dutyscheduling.approval.repository;

import com.boot.data.dutyscheduling.approval.entity.DutySchedulingApprovalLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 98548 on 2019/2/12.
 */
public interface DutySchedulingApprovalLogRepository extends JpaRepository<DutySchedulingApprovalLog, Long> {
}
