package com.boot.data.dutyscheduling.approval.service;

import com.boot.data.dutyscheduling.approval.entity.DutyScheduling;

import java.util.List;

/**
 * @author 98548
 * @create 2019-01-31 15:04
 * @description
 */
public interface DutySchedulingService {
    void updateOrInsert(DutyScheduling dutyScheduling);

    List<DutyScheduling> getAll();

    DutyScheduling approval(long dutySchedulingId, String comments, int state);
}
