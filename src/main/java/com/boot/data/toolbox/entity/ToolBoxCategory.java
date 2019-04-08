package com.boot.data.toolbox.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 98548
 * @create 2019-03-20 10:33
 * @description
 */
@Data
@Entity
@Table(name = "t_toolbox_category")
public class ToolBoxCategory implements Serializable {

    private static final long serialVersionUID = -2458562972451164194L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT  COMMENT '工具分类ID'")
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(255) COMMENT '分类标题'")
    private String name;
    @Column(name = "pid", columnDefinition = "BIGINT  COMMENT '父级ID'")
    private Long pID;
    @Column(name = "order_index", columnDefinition = "SMALLINT COMMENT '排序规则'")
    private short orderIndex;
    @Column(name = "create_user_id", columnDefinition = "BIGINT COMMENT '创建者ID'")
    private Long createUserID;
    @Column(name = "update_user_id", columnDefinition = "BIGINT COMMENT '修改人ID'")
    private Long updateUserID;
    @Column(name = "create_user", columnDefinition = "VARCHAR(255) COMMENT '创建人'")
    private String createUser;
    @Column(name = "update_user", columnDefinition = "VARCHAR(255) COMMENT '修改人'")
    private String updateUser;
    @Column(name = "create_date", columnDefinition = "datetime COMMENT '创建日期'")
    private Date createDate;
    @Column(name = "update_date", columnDefinition = "datetime COMMENT '最近修改日期'")
    private Date updateDate;
    @Column(name = "data_state", columnDefinition = "TINYINT unsigned COMMENT '数据状态(1:正常使用;255:删除)'")
    private Integer dataState;

    @Transient
    private List<ToolBoxCategory> categories;
}
