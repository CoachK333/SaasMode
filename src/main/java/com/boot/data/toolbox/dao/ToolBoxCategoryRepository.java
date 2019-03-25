package com.boot.data.toolbox.dao;


import com.boot.data.toolbox.entity.ToolBoxCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 98548 on 2019/3/25.
 */
public interface ToolBoxCategoryRepository extends JpaRepository<ToolBoxCategory, Long> {

    @Query(value = "SELECT * FROM t_toolbox_category WHERE id = ?1 AND data_state = 1", nativeQuery = true)
    ToolBoxCategory getOneWithDataState(Long id);

    @Query(value = "SELECT * FROM t_toolbox_category WHERE pid = ?1 AND data_state =1", nativeQuery = true)
    List<ToolBoxCategory> getAllByPID(Long Pid);

    @Query(value = "SELECT * FROM t_toolbox_category WHERE id = ?1 AND data_state = 1", nativeQuery = true)
    ToolBoxCategory getOneWithID(Long id);

    @Query(value = "SELECT * FROM t_toolbox_category WHERE data_state = ?1", nativeQuery = true)
    List<ToolBoxCategory> getAllWithDataState(Long dataState);
}
