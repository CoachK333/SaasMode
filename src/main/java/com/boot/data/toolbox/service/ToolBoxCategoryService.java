package com.boot.data.toolbox.service;

import com.boot.data.toolbox.entity.ToolBoxCategory;

import java.util.List;

/**
 * Created by 98548 on 2019/3/25.
 */
public interface ToolBoxCategoryService {
    ToolBoxCategory updateOrAdd( String id, String title, String pNo, String orderIndex);

    List<ToolBoxCategory> getTreeList();
}
