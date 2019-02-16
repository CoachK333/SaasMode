package com.boot.data.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author 98548
 * @create 2019-02-14 17:48
 * @description
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String upload(MultipartFile file) {
        return file.getName() + file.getOriginalFilename();
    }
}
