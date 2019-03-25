package com.boot.data.toolbox.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-03-20 11:42
 * @description
 */
@Data
@Entity
@Table(name = "t_toolbox_comment")
public class ToolboxComment {
    @Id
    private Long id;
    private Long toolBoxID;
    private String content;
    private Long commentUserID;
    private String commentUser;
    private Date createDate;

    @Column(name = "dataState", columnDefinition = "tinyint(4) unsigned COMMENT '数据状态(1:正常使用;255:删除)'")
    private Integer dataState;
}
