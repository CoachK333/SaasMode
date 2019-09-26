package com.boot.data.jna;

/**
 * @author 98548
 * @create 2019-08-29 9:52
 * @description
 */
public class MainApp {

    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello,%s World!", "jennef");
        StdCallDllLibrary.INSTANCE.printf("Hello,%s World!", "jennef");
    }
}
