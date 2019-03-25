package com.boot.data.toolbox.controller;

import com.boot.data.dto.Result;
import com.boot.data.toolbox.ResultUtil;
import com.boot.data.toolbox.entity.ToolBoxCategory;
import com.boot.data.toolbox.service.ToolBoxCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 98548
 * @create 2019-03-21 20:10
 * @description
 */
@RestController
@RequestMapping("/toolBoxCategory")
public class ToolBoxCategoryController {

    @Autowired
    private ToolBoxCategoryService toolBoxCategoryService;


    //新增/编辑
    @PostMapping("/updateOrAdd")
    @ApiOperation("新增(或更新)工具类型")
    public Result updateOrAdd(HttpServletRequest request,
                              @ApiParam(name = "id", value = "工具ID(修改时必填)") String id,
                              @ApiParam(name = "title", value = "工具标题(必填)") String title,
                              @ApiParam(name = "pNo", value = "父菜单ID") String pNo,
                              @ApiParam(name = "orderIndex", value = "分类排名(必填)") String orderIndex) {
        if (StringUtils.isAnyBlank(title, orderIndex)) {
            return new ResultUtil<>().setErrorMsg("请检查必填参数");
        }
        //pNo如果没传,该分类为顶级菜单;
        if (StringUtils.isBlank(pNo)) {
            pNo = "0";
        }
        toolBoxCategoryService.updateOrAdd(id, title, pNo, orderIndex);
        return new ResultUtil().setSuccessMsg("ok");
    }


    //列表(树形)
    @GetMapping("/getTreeList")
    @ApiOperation("获取树形列表")
    public Result getTreeList() {
        List<ToolBoxCategory> categoryList = toolBoxCategoryService.getTreeList();
        return new ResultUtil<>().setData(categoryList);
    }
}
