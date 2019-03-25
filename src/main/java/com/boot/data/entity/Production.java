package com.boot.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 98548
 * @create 2019-03-20 14:19
 * @description
 */
@Data
@Entity
@Table(name = "t_product")
public class Production {

    @Id
    private Long id;

    private String name;

    private String version;
}
