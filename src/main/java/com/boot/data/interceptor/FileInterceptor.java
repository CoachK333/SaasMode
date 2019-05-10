package com.boot.data.interceptor;

import com.alibaba.fastjson.JSON;
import com.boot.data.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 98548
 * @create 2019-04-23 14:48
 * @description
 */
@Component
@Slf4j
public class FileInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MultipartHttpServletRequest request1 = null;
        try {
            request1 = (MultipartHttpServletRequest) request;
        } catch (Exception e) {
            log.info("请求体没有文件...");
            return true;
        }
        List<MultipartFile> files = request1.getFiles("file");
        if (files == null || files.isEmpty()) {
            return true;
        }
        for (MultipartFile file : files) {
            if (!FileUtils.filter(file)) {
                response.getWriter().print(JSON.toJSONString("hehe,CNM"));
                return false;
            }
        }
        return true;
    }
}
