package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.boot.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author 98548
 * @create 2019-02-14 17:48
 * @description
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public String upload(MultipartFile file) {
        return file.getName() + file.getOriginalFilename();
    }

    @Override
    public List<Product> test() {
        Query query = entityManager.createNativeQuery("SELECT * FROM t_product ", Product.class);
        List<Product> list = query.getResultList();
        System.out.println(JSON.toJSONString(list));
        return list;
    }

    @Override
    public List test111(String s) {
        Query query = entityManager.createNativeQuery("select * from t_leave where id = ?1 AND  id = ?1");
        query.setParameter(1, s);
        List list = query.getResultList();
        return list;

    }
}
