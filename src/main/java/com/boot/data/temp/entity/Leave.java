package com.boot.data.temp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import sun.awt.windows.WComponentPeer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 98548
 * @create 2019-04-12 15:47
 * @description
 */
@Data
@Entity
@Table(name = "t_leave")
public class Leave {
    @Id
    private Long id;
    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_date")
    private Date endDate;
}
