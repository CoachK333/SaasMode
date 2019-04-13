package com.boot.data.temp.dao;

import com.boot.data.temp.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 98548 on 2019/4/12.
 */
public interface LeaveRepository extends JpaRepository<Leave, Long>, JpaSpecificationExecutor<Leave> {
}
