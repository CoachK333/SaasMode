package com.boot.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotifKeys implements Serializable {

    private static final long serialVersionUID = 762438874990066128L;

    private Long userID;
    private Long businessID;
}