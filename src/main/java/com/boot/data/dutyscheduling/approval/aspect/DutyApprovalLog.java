package com.boot.data.dutyscheduling.approval.aspect;

import java.lang.annotation.*;

/**
 * Created by 98548 on 2019/2/14.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface DutyApprovalLog {
}
