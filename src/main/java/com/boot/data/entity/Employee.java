package com.boot.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 98548
 * @create 2019-07-09 18:00
 * @description
 */
@Data
@AllArgsConstructor
public class Employee {

    private Integer id;
    private String name;
    private Integer age;
    private Double salary;
}
