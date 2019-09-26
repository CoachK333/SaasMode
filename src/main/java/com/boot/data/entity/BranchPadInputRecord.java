package com.boot.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-08-23 8:49
 * @description
 */
@Entity
@Table(name = "branch_pad_input_record")
@Data
public class BranchPadInputRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //bigint(20)
    private String userNo;        //varchar(40)
    private String userName;    //varchar(100)
    private String imgUrl;        //varchar(255)
    private Long jpgId;        //bigint(20)
    private Integer type;       //tinyint(4)
    private Integer operation;  //tinyint(4)
    private Boolean result;     //bit(1)
    private String ip;          //varchar(100)
    private Date date;          //下发时间 datetime
    private Date createdAt;    //datetime
}
