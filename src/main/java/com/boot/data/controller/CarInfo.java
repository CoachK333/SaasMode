package com.boot.data.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "carNo")
public class CarInfo {
    String carNo; // 车号
    int timeoutCount; // 从园区入口到停车场或停车场到园区出口超时
    int trunkOpenCount; // 打开过后备箱
    int enteredForbiddenArea; // 进入过禁行区
    int enteredHighLevelOrVipParkingArea; // 进入过领导或VIP停车区
    int enteredSensitiveArea; // 进入过敏感区。普通员工或普通访客进入敏感区，出园区前需要检查后备箱
    long expireTime = Long.MAX_VALUE;

    CarInfo(String carNo) {
        this.carNo = carNo;
    }
}