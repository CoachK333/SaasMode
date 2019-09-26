package com.boot.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Teacher {
    private String name;
    private List<String> address = new ArrayList<>();
}