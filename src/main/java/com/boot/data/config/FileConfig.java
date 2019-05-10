package com.boot.data.config;

import com.boot.data.interceptor.FileInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 98548
 * @create 2019-04-23 15:17
 * @description
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Autowired
    private FileInterceptor fileInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(fileInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("");
    }
}
