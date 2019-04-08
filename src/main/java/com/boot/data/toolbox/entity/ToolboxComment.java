package com.boot.data.toolbox.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-03-20 11:42
 * @description
 */
@Data
@Entity
@Table(name = "t_toolbox_comment")
@AllArgsConstructor
@NoArgsConstructor
public class ToolboxComment implements Serializable {
    private static final long serialVersionUID = -8118477070354927942L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT  COMMENT '评论ID'")
    private Long id;
    @Column(name = "toolbox_id", columnDefinition = "BIGINT  COMMENT '工具ID'")
    private Long toolBoxID;
    @Column(name = "content", columnDefinition = "TEXT COMMENT '评论内容'")
    private String content;
    @Column(name = "comment_user_id", columnDefinition = "BIGINT  COMMENT '评论者ID'")
    private Long commentUserID;
    @Column(name = "comment_user", columnDefinition = "VARCHAR(255) COMMENT '评论者'")
    private String commentUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date", columnDefinition = "datetime COMMENT '创建日期'")
    private Date createDate;
    @Column(name = "data_state", columnDefinition = "tinyint(4) unsigned COMMENT '数据状态(1:正常使用;255:删除)'")
    private Integer dataState;

    @Transient
    private String commentUserIconURL;//评论者头像

    @Transient
    private boolean revocable;  //是否可撤销(删除)

    public ToolboxComment(Long toolBoxID, String content, String commentUser, Date createDate, String commentUserIconURL) {
        this.toolBoxID = toolBoxID;
        this.content = content;
        this.commentUser = commentUser;
        this.createDate = createDate;
        this.commentUserIconURL = commentUserIconURL;
    }
}
