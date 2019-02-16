package com.boot.data.dutyscheduling.approval.aspect;

import com.boot.data.dutyscheduling.approval.entity.DutyScheduling;
import com.boot.data.dutyscheduling.approval.entity.DutySchedulingApprovalLog;
import com.boot.data.dutyscheduling.approval.repository.DutySchedulingApprovalLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 98548
 * @create 2019-02-12 9:59
 * @description
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private DutySchedulingApprovalLogRepository logRepository;

    @Pointcut("execution(public * com.boot.data.dutyscheduling.approval.service..approval(..))")
    public void dutySchedulingApprovalLog() {
    }

    @Before("dutySchedulingApprovalLog()")
    public void addApprovalLog(JoinPoint joinPoint) {

        Object[] objects = joinPoint.getArgs();
        long dutySchedulingId = (long) objects[0];
        String comments = (String) objects[1];
        int state = (int) objects[2];
        DutySchedulingApprovalLog approvalLog = new DutySchedulingApprovalLog();
        approvalLog.setDuty_scheduling_id(dutySchedulingId);
        approvalLog.setComments(comments);
        approvalLog.setApprovalState(state);
        approvalLog.setApprovalDate(new Date());
        logRepository.save(approvalLog);
    }

    @AfterReturning(pointcut = "dutySchedulingApprovalLog()", returning = "returnVal")
    public void addLog(JoinPoint joinPoint, Object returnVal) {
       DutyScheduling dutyScheduling = (DutyScheduling) returnVal;
        System.out.println(dutyScheduling);
    }
}
