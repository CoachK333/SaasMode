package com.boot.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-02-19 15:03
 * @description
 */
@Data
@Entity
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GenericGenerator(name = "idGenerator", strategy = "uuid")
//    @GeneratedValue(generator = "idGenerator")
    private Long id;

    private String name;

    @Column(name = "description")
    private String desc;

    @Column(columnDefinition = "text comment '富文本描述'")
    private String comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
