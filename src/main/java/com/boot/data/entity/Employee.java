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
    private Status status;

    public Employee(Integer id, Integer age) {
        this.id = id;
        this.age = age;
    }

    public Employee(Integer id, String name, Integer age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public enum Status {
        BUSY,
        FREE,
        VACATION;
    }
}
