package com.boot.data.toolbox.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 98548
 * @create 2019-03-20 19:24
 * @description
 */
@Data
@Entity
@Table(name = "t_toolbox_log")
public class ToolBoxLog {

    @Id
    private String id;
}
