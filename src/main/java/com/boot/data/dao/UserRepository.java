package com.boot.data.dao;

import com.boot.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

/**
 * @author 98548
 * @create 2019-02-19 15:05
 * @description
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM t_user", nativeQuery = true)
    List<Object[]> getAll();
}
