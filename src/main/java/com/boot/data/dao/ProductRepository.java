package com.boot.data.dao;

import com.boot.data.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 98548 on 2019/3/20.
 */
public interface ProductRepository extends JpaRepository<Production, Long> {
}
