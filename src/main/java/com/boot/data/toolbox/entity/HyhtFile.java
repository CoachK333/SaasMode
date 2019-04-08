package com.boot.data.toolbox.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by libin on .
 */
@Data
@Entity
@Table(name = "t_file")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HyhtFile implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "FileID")
    private long fileId;
    @Column(name = "FileName")
    private String fileName;
    @Column(name = "FileExt")
    private String fileExt;
    @Column(name = "BusinessID")
    private long businessId;
    @Column(name = "BusinessType")
    private int businessType;
    @Column(name = "FilePath")
    private String filePath;
    @Column(name = "CreateDate")
    private Date createDate;
    @Column(name = "CreateUserID")
    private String createUserId;
    @Column(name = "CreateUserName")
    private String createUserName;
    @Column(name = "DataState")
    private int dataState;
}
