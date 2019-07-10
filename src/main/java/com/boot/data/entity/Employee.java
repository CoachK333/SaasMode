package com.boot.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 98548
 * @create 2019-07-09 18:00
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String name;
    private Integer age;
    private Double salary;

    public Employee(Integer id, Integer age) {
        this.id = id;
        this.age = age;
    }
}
