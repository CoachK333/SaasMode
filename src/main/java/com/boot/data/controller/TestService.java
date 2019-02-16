package com.boot.data.controller;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by 98548 on 2019/2/14.
 */
public interface TestService {
    String upload(MultipartFile file);
}
