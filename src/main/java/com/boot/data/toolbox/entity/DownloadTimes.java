package com.boot.data.toolbox.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 98548
 * @create 2019-04-10 10:01
 * @description 附件下载次数统计表(businessID 为业务ID, 不是文件ID)
 */
@Data
@Entity
@Table(name = "t_download_times")
public class DownloadTimes {

    @Id
    @Column(name = "bussiness_id", columnDefinition = "BIGINT  COMMENT '业务id'")
    private Long businessID;

    @Column(name = "times", columnDefinition = "BIGINT  COMMENT '下载次数'")
    private Long times;

}
