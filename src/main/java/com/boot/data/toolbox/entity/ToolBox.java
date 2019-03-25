package com.boot.data.toolbox.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-03-14 15:59
 * @description
 */
@Entity
@Table(name = "t_toolbox")
@Data
public class ToolBox implements Serializable {
    private static final long serialVersionUID = -1027370413776020876L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT(20)  COMMENT '工具箱id'")
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(255) COMMENT '工具标题'")
    private String title;

    @Column(name = "category_id", columnDefinition = "BIGINT(20) COMMENT '工具分类ID'")
    private Long categoryID;

    @Column(name = "label", columnDefinition = "VARCHAR(255) COMMENT '工具箱关键字(或标签)'")
    private String label;

    @Column(name = "iconID", columnDefinition = "BIGINT(20) COMMENT '图标ID'")
    private Long iconID;


    @Column(name = "instructions", columnDefinition = "TEXT COMMENT '使用说明'")
    private String instructions;

    @Column(name = "attachmentIDs", columnDefinition = "VARCHAR(255) COMMENT '工具附件ID(多个)'")
    private String attachmentIDs;
    @Column(name = "additionalAttachmentIDs", columnDefinition = "VARCHAR(255) COMMENT '其他附件ID(多个)'")
    private String additionalAttachmentIDs;

    @Column(name = "createUserID", columnDefinition = "BIGINT(20) COMMENT '创建人ID'")
    private Long createUserID;
    @Column(name = "updateUserID", columnDefinition = "BIGINT(20) COMMENT '修改人ID'")
    private Long updateUserID;

    @Column(name = "createUser", columnDefinition = "VARCHAR(255) COMMENT '创建人'")
    private String createUser;
    @Column(name = "updateUser", columnDefinition = "VARCHAR(255) COMMENT '最近修改人'")
    private String updateUser;

    @Column(name = "createDate", columnDefinition = "datetime COMMENT '创建日期'")
    private Date createDate;
    @Column(name = "updateDate", columnDefinition = "datetime COMMENT '修改日期'")
    private Date updateDate;

    @Column(name = "version", columnDefinition = "VARCHAR(255) COMMENT '当前版本号'")
    private String version;

    @Column(name = "dataState", columnDefinition = "tinyint(4) unsigned COMMENT '数据状态(1:正常使用;255:删除)'")
    private Integer dataState;

}
