package com.boot.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 98548
 * @create 2019-02-19 15:03
 * @description
 */
@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "description")
    private String desc;

}
