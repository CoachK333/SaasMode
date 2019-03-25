package com.boot.data.toolbox.service.impl;


import com.boot.data.toolbox.dao.ToolBoxCategoryRepository;
import com.boot.data.toolbox.dao.ToolBoxRepository;
import com.boot.data.toolbox.entity.ToolBox;
import com.boot.data.toolbox.entity.ToolBoxCategory;
import com.boot.data.toolbox.service.ToolBoxCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 98548
 * @create 2019-03-25 11:45
 * @description
 */
@Service
public class ToolBoxCategoryServiceImpl implements ToolBoxCategoryService {

    @Autowired
    private ToolBoxCategoryRepository toolBoxCategoryRepository;
    @Autowired
    private ToolBoxRepository toolBoxRepository;

    @Override
    public ToolBoxCategory updateOrAdd(String id, String title, String pNo, String orderIndex) {
        ToolBoxCategory toolBoxCategory;
        if (StringUtils.isBlank(id)) {
            toolBoxCategory = new ToolBoxCategory();
            toolBoxCategory.setCreateUserID(123L);
            toolBoxCategory.setCreateUser("123");
            toolBoxCategory.setCreateDate(new Date());
        } else {
            toolBoxCategory = toolBoxCategoryRepository.getOneWithDataState(Long.valueOf(id));
        }
        toolBoxCategory.setName(title);
        toolBoxCategory.setPID(Long.valueOf(pNo));
        toolBoxCategory.setOrderIndex(Short.valueOf(orderIndex));
        toolBoxCategory.setUpdateUserID(123L);
        toolBoxCategory.setUpdateUser("123");
        toolBoxCategory.setUpdateDate(new Date());
        toolBoxCategory.setDataState(1);

        return toolBoxCategoryRepository.save(toolBoxCategory);
    }


    @Override
    public List<ToolBoxCategory> getTreeList() {
        List<ToolBoxCategory> categories = toolBoxCategoryRepository.getAllWithDataState(1L);
        return formTree(categories, 0L);
    }

    private List<ToolBoxCategory> formTree(List<ToolBoxCategory> elements, Long pid) {
        List<ToolBoxCategory> childList = new ArrayList<>();
        for (ToolBoxCategory element : elements) {
            if (pid.equals(element.getPID())) {
                childList.add(element);
            }
        }
        for (ToolBoxCategory element : elements) {
            element.setCategories(formTree(elements, element.getId()));
        }
        if (childList.isEmpty()) {
            return null;
        }
        return childList;
    }
}
