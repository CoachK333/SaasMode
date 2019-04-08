package com.boot.data.toolbox.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-03-22 11:39
 * @description
 */
@Data
@Entity
@Table(name = "t_icon_file")
public class IconFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT  COMMENT '头像id'")
    private Long id;
    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    private String fileName;
    @Column(name = "file_ext", columnDefinition = "VARCHAR(255) COMMENT '文件拓展名'")
    private String fileExt;
    @Column(name = "bussiness_type", columnDefinition = "tinyint unsigned COMMENT '业务类型(1:工具箱)'")
    private Integer businessType;
    @Column(name = "business_id", columnDefinition = "BIGINT  COMMENT '业务id'")
    private Long businessID;
    @Column(name = "default_flag", columnDefinition = "bit(1) NOT NULL COMMENT '是否为默认头像(0:不是;1:是)'")
    private Boolean defaultFlag;
    @Column(name = "order_index", columnDefinition = "TINYINT COMMENT '排序规则'")
    private Integer orderIndex;
    @Column(name = "file_path", columnDefinition = "TEXT COMMENT '文件路径'")
    private String filePath;
    @Column(name = "create_date", columnDefinition = "datetime COMMENT '创建日期'")
    private Date createDate;
    @Column(name = "create_user_id", columnDefinition = "BIGINT COMMENT '创建者ID'")
    private Long createUserID;
    @Column(name = "create_user", columnDefinition = "VARCHAR(255) COMMENT '创建人'")
    private String createUser;
    @Column(name = "data_state", columnDefinition = "tinyint(4) unsigned COMMENT '数据状态(1:正常使用;255:删除)'")
    private Integer dateState;
}
