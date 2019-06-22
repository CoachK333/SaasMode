package com.boot.data.dao;

import com.boot.data.entity.Production;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by 98548 on 2019/3/20.
 */
public interface ProductRepository extends JpaRepository<Production, Long> {
    @Query(value = "select * from t_product", nativeQuery = true)
    Page<Production> getAll(Pageable pageable);
}
