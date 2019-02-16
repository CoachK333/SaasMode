package com.boot.data.dao;

import com.boot.data.dto.UserDto;
import com.boot.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author 98548
 * @create 2019-01-17 14:01
 * @description
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Query(value = "SELECT a.id AS id,a.name AS name,a.dep AS dep,b.depid AS depid  FROM `user` a  LEFT JOIN user_dep b ON a.id = b.userid WHERE a.`name` = ?1", nativeQuery = true)
    List<UserDto> getByName(String name);

    @Query(value = "SELECT name,dep FROM `user`", nativeQuery = true)
    List<Map<String, String>> getByName();

}
