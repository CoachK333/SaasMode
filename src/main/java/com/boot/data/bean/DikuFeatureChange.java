package com.boot.data.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DikuFeatureChange {
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private Long id;
    private String group;
    private String name;
    private String employeeId;
    private String imageFeature;
    private String imageUrl;
    private String source; // ISANY, SHR, IMPORT
    private boolean deleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = "Asia/Shanghai")
    private Date updatedAt;
}
