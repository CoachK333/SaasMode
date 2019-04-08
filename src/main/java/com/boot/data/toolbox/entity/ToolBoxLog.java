package com.boot.data.toolbox.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-03-20 19:24
 * @description
 */
@Data
@Entity
@Table(name = "t_toolbox_log")
public class ToolBoxLog implements Serializable {

    private static final long serialVersionUID = -4650918827115035344L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT  COMMENT '工具箱日志ID'")
    private String id;

    @Column(name = "toolbox_id", columnDefinition = "BIGINT  COMMENT '工具箱ID'")
    private Long toolBoxID;

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

    @Column(name = "attachment_ids", columnDefinition = "VARCHAR(255) COMMENT '工具附件ID(多个)'")
    private String attachmentIDs;

    @Column(name = "additional_attachment_ids", columnDefinition = "VARCHAR(255) COMMENT '其他附件ID(多个)'")
    private String additionalAttachmentIDs;

    @Column(name = "create_user_id", columnDefinition = "BIGINT(20) COMMENT '创建人ID'")
    private Long createUserID;

    @Column(name = "create_user", columnDefinition = "VARCHAR(255) COMMENT '创建人'")
    private String createUser;

    @Column(name = "create_date", columnDefinition = "datetime COMMENT '创建日期'")
    private Date createDate;

    @Column(name = "version", columnDefinition = "VARCHAR(255) COMMENT '当前版本号'")
    private String version;

}
