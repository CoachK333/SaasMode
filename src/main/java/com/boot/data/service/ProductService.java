package com.boot.data.service;

import com.boot.data.entity.Production;
import org.springframework.data.domain.Page;

/**
 * Created by 98548 on 2019/3/20.
 */
public interface ProductService {

    Production getOne(String id);

    Page<Production> getAll();
}
