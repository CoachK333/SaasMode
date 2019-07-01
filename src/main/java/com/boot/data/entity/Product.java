package com.boot.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 98548
 * @create 2019-06-24 16:42
 * @description
 */
@Entity
@Table(name = "t_product")
@Data
public class Product {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
}
