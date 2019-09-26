package com.boot.data.bean;

/**
 * @author 98548
 * @create 2019-09-04 17:41
 * @description 停车场摄像头
 */
public enum ParkingCamera {
    NAN_KOU_PARKING_LOT_01("10.193.160.130", "南口停车场01", "admin", "sany318!", "DAHUA", "南口"),
    NAN_KOU_PARKING_LOT_02("10.193.160.134", "南口停车场01", "admin", "sany318!", "HIK", "南口");


    private String ip;      //摄像头ip
    private String name;    //摄像头名称
    private String username;    //登录账户
    private String password;    //密码
    private String producer;    //服务商
    private String area;        //区域

    ParkingCamera(String ip, String name, String username, String password, String producer, String area) {
        this.ip = ip;
        this.name = name;
        this.username = username;
        this.password = password;
        this.producer = producer;
        this.area = area;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProducer() {
        return producer;
    }

    public String getArea() {
        return area;
    }
}
