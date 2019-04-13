package com.boot.data.temp.service;

import com.boot.data.temp.dao.LeaveRepository;
import com.boot.data.temp.entity.Leave;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 98548
 * @create 2019-04-12 15:51
 * @description
 */
@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<Leave> list(String startDateStr, String endDateStr) {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT * FROM t_leave ");

        sb.append(" WHERE 1 = 1 ");

        if (StringUtils.isNotBlank(startDateStr)) {
            sb.append(" AND start_date >= '").append(startDateStr).append("' ");
        }
        if (StringUtils.isNotBlank(endDateStr)) {
            sb.append(" AND end_date <= '").append(endDateStr).append("' ");
        }
        if (StringUtils.isNoneBlank(startDateStr, endDateStr)) {
            sb.append(" OR start_date BETWEEN '").append(startDateStr).append("' AND '").append(endDateStr).append("' ");
            sb.append(" OR end_date BETWEEN '").append(startDateStr).append("' AND '").append(endDateStr).append("' ");
            sb.append(" OR '").append(startDateStr).append("' BETWEEN start_date AND end_date ");
            sb.append(" OR '").append(endDateStr).append("' BETWEEN start_date AND end_date ");
        }

        String sql = sb.toString();
        List resultList = entityManager.createNativeQuery(sql, Leave.class).getResultList();
        return resultList;
    }

}
