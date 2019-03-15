package com.boot.data.controller;

import lombok.extern.slf4j.Slf4j;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * @author 98548
 * @create 2018-12-03 18:44
 * @description
 */
@Slf4j
public class MainApp012 {

    public static void main(String[] args) throws SQLException {
        String s = "123";

        Blob blob = new SerialBlob(s.getBytes());
        System.out.println();
    }

}
