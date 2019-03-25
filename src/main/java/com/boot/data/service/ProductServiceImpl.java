package com.boot.data.service;

import com.boot.data.dao.ProductRepository;
import com.boot.data.entity.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 98548
 * @create 2019-03-20 14:23
 * @description
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Production getOne(String id) {

        Long aLong = Long.valueOf(id);
        return productRepository.getOne(aLong);
    }
}
