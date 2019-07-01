package com.boot.data.controller;

import com.boot.data.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 98548 on 2019/2/14.
 */
public interface TestService {
    String upload(MultipartFile file);


    List<Product> test();

    List test111(String s);
}
