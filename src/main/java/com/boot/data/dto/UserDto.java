package com.boot.data.dto;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author 98548
 * @create 2019-01-17 14:03
 * @description
 */
public interface UserDto {

    String getId();

    String getName();

    String getDep();

    String getDepid();

}
