package com.boot.data.dutyscheduling.approval.service.impl;

import com.boot.data.dutyscheduling.approval.entity.DutyScheduling;
import com.boot.data.dutyscheduling.approval.repository.DutySchedulingApprovalLogRepository;
import com.boot.data.dutyscheduling.approval.repository.DutySchedulingRepository;
import com.boot.data.dutyscheduling.approval.service.DutySchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 98548
 * @create 2019-01-31 15:04
 * @description
 */
@Service
@Transactional
public class DutySchedulingServiceImpl implements DutySchedulingService {

    @Autowired
    private DutySchedulingRepository dutySchedulingRepository;

    @Autowired
    private DutySchedulingApprovalLogRepository approvalLogRepository;


    @Override
    public void updateOrInsert(DutyScheduling dutyScheduling) {
        //添加/更新
        dutySchedulingRepository.save(dutyScheduling);
    }

    @Override
    public List<DutyScheduling> getAll() {
        return dutySchedulingRepository.findAll();
    }

    @Override
    public DutyScheduling approval(long dutySchedulingId, String comments, int state) {
        DutyScheduling dutyScheduling = dutySchedulingRepository.getOne(dutySchedulingId);
        dutyScheduling.setApprovalState(state);
        DutyScheduling scheduling = dutySchedulingRepository.save(dutyScheduling);
        return scheduling;
    }
}
