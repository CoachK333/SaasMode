package com.boot.data.toolbox.controller;

import com.boot.data.dto.Result;
import com.boot.data.toolbox.service.ToolBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author 98548
 * @create 2019-03-18 11:40
 * @description
 */
@Slf4j
@Api(description = "工具箱接口服务")
@RestController
@RequestMapping("/toolBox")
public class ToolBoxController {

    @Autowired
    private ToolBoxService toolBoxService;

    @PostMapping("/updateOrAdd")
    @ApiOperation("工具箱--更新/添加工具")
    public Result updateOrAdd(HttpServletRequest request,
                              String title,
                              String category,
                              String label,
                              String iconID,
                              String instructions,
                              String attachmentIDs,
                              String additionalAttachmentIDs) {

        return null;
    }
    //工具图标上传

    //附件(其他附件,富文本附件)上传


}
