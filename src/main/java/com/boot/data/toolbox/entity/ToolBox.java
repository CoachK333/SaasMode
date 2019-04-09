package com.boot.data.toolbox.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Column(name = "icon_id", columnDefinition = "BIGINT(20) COMMENT '图标ID'")
    private Long iconID;

    @Column(name = "instructions", columnDefinition = "TEXT COMMENT '使用说明'")
    private String instructions;

    @Column(name = "descriptions", columnDefinition = "TEXT COMMENT '工具描述'")
    private String descriptions;

    @Column(name = "attachment_ids", columnDefinition = "VARCHAR(255) COMMENT '工具附件ID(多个)'")
    private String attachmentIDs;
    @Column(name = "additional_attachment_ids", columnDefinition = "VARCHAR(255) COMMENT '其他附件ID(多个)'")
    private String additionalAttachmentIDs;

    @Column(name = "create_user_id", columnDefinition = "BIGINT(20) COMMENT '创建人ID'")
    private Long createUserID;
    @Column(name = "update_user_id", columnDefinition = "BIGINT(20) COMMENT '修改人ID'")
    private Long updateUserID;

    @Column(name = "create_user", columnDefinition = "VARCHAR(255) COMMENT '创建人'")
    private String createUser;
    @Column(name = "update_user", columnDefinition = "VARCHAR(255) COMMENT '最近修改人'")
    private String updateUser;

    @Column(name = "create_date", columnDefinition = "datetime COMMENT '创建日期'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @Column(name = "update_date", columnDefinition = "datetime COMMENT '修改日期'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @Column(name = "version", columnDefinition = "VARCHAR(255) COMMENT '当前版本号'")
    private String version;

    @Column(name = "data_state", columnDefinition = "tinyint(4) unsigned COMMENT '数据状态(1:正常使用;255:删除)'")
    private Integer dataState;


    @Transient
    private boolean revocable;      //是否可删除
    @Transient
    private boolean resubmittable;  //是否可修改
    @Transient
    private boolean downloadable;   //是否可下载

    @Transient
    private List<ToolBoxLog> toolBoxLogs;
    @Transient
    private List<HyhtFile> attachments;
    @Transient
    private List<HyhtFile> addtionalAttachments;

}
