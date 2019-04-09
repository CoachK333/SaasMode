package com.boot.data.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public class VideoDaoImpl {

	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * @param parameter
	 * @param pageable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<VideoExtend> findVideoList(VideoParam parameter, Pageable pageable) {
		StringBuilder dataSql = new StringBuilder("SELECT v.fee_stat,v.action_id,v.video_icon_audit,"
				+ "v.video_url,v.video_wide,v.video_high,v.privilege_list,v.video_lable,"
				+ "v.praise_cnt,v.play_cnt,v.comment_cnt,v.resend_cnt,v.gift_cnt,v.reward_cnt,"
				+ "v.play_cnt,v.position_name,v.longitude,v.latitude,v.cos_lat,v.update_time,"
				+ "v.video_id,v.video_customer_id,v.video_title,v.aliyun_video_id,v.create_time,"
				+ "v.video_status,a.remark,v.video_icon,v.report_cnt,v.fee_stat,"
				+ "v.video_lable,c.sex "
				+ " FROM t_video v join t_customer c on v.video_customer_id = c.customer_id "
				+ "left join t_action a on v.action_id = a.action_id ");
			StringBuilder countSql = new StringBuilder("SELECT count(1) FROM t_video v ");
//				+ "left join t_action a on v.video_id = a.video_id ");
		
		//拼接where条件
		StringBuilder whereSql = new StringBuilder(" WHERE 1 = 1");
		if (StringUtils.isNotEmpty(parameter.getCustomerId())) {
			whereSql.append(" AND v.video_customer_id = :customerId");
		}
		
		if (StringUtils.isNotEmpty(parameter.getStatus())) {
			whereSql.append(" AND v.video_status = :status");
		}

		if (parameter.getEndTime() != null && parameter.getStartTime() != null) {
			whereSql.append(" AND v.create_time >= :startTime AND v.create_time <= :endTime");
		}

		if (StringUtils.isNotEmpty(parameter.getVideoTitle())) {
			whereSql.append(" AND v.video_title like concat('%',:videoTitle,'%')");
		}													

		if(parameter.getSex()!= null&&parameter.getSex()!=-1){
			countSql.append(" join t_customer c on v.video_customer_id = c.customer_id ");
			whereSql.append(" AND c.sex = :sex");
		}
		//组装sql语句
		dataSql.append(whereSql).append(" order by v.create_time desc");
		countSql.append(whereSql);
		
		//创建本地sql查询实例
		Query dataQuery = entityManager.createNativeQuery(dataSql.toString(), VideoExtend.class);
		Query countQuery = entityManager.createNativeQuery(countSql.toString());
		
		//设置参数
		if (StringUtils.isNotEmpty(parameter.getCustomerId())) {
			dataQuery.setParameter("customerId", parameter.getCustomerId());
			countQuery.setParameter("customerId", parameter.getCustomerId());
		}
		if (parameter.getEndTime() != null && parameter.getStartTime() != null) {
			dataQuery.setParameter("startTime", parameter.getStartTime(), TemporalType.TIMESTAMP);
			dataQuery.setParameter("endTime", parameter.getEndTime(), TemporalType.TIMESTAMP);
			countQuery.setParameter("startTime", parameter.getStartTime(), TemporalType.TIMESTAMP);
			countQuery.setParameter("endTime", parameter.getEndTime(), TemporalType.TIMESTAMP);
		}
		if (StringUtils.isNotEmpty(parameter.getStatus())) {
			dataQuery.setParameter("status", parameter.getStatus());
			countQuery.setParameter("status", parameter.getStatus());
		}
		if (StringUtils.isNotEmpty(parameter.getVideoTitle())) {
			dataQuery.setParameter("videoTitle", parameter.getVideoTitle());
			countQuery.setParameter("videoTitle", parameter.getVideoTitle());
		}
		if(parameter.getSex()!= null&&parameter.getSex()!=-1){
			dataQuery.setParameter("sex",parameter.getSex());
			countQuery.setParameter("sex",parameter.getSex());
		}
		//设置分页
		dataQuery.setFirstResult((int) pageable.getOffset());
		dataQuery.setMaxResults(pageable.getPageSize());
		BigInteger count = (BigInteger) countQuery.getSingleResult();
		Long total = count.longValue();
		List<VideoExtend> content2 = total > pageable.getOffset() ? dataQuery.getResultList() : Collections.<VideoExtend> emptyList();
		return new PageImpl<>(content2, pageable, total);
	}
}