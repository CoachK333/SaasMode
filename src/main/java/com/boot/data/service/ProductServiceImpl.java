package com.boot.data.service;

import com.boot.data.dao.ProductRepository;
import com.boot.data.entity.Production;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Page<Production> getAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Production> productionPage = productRepository.getAll(pageable);
        List<Production> content = productionPage.getContent();
        rowData(content);
        return productionPage;
    }

    private void rowData(List<Production> list) {
        list.forEach(production -> {
            if (production.getName().equals("zhangsan")) {
                production.setName("张三");
            } else if (production.getName().equals("lisi")) {
                production.setName("李四");
            }
        });
    }
}
