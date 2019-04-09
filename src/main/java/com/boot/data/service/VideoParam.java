package com.boot.data.service;

import java.util.Date;

/**
 * @author 98548
 * @create 2019-04-09 15:06
 * @description
 */
public class VideoParam {
    private CharSequence customerId;
    private CharSequence status;
    private CharSequence videoTitle;
    private Integer sex;
    private Date endTime;
    private Date startTime;

    public CharSequence getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CharSequence customerId) {
        this.customerId = customerId;
    }

    public CharSequence getStatus() {
        return status;
    }

    public CharSequence getVideoTitle() {
        return videoTitle;
    }

    public Integer getSex() {
        return sex;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getStartTime() {
        return startTime;
    }
}
