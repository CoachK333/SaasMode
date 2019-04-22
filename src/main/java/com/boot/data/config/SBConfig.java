package com.boot.data.config;

import com.boot.data.interceptor.SBInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 98548
 * @create 2019-04-03 11:31
 * @description
 */
@Configuration
public class SBConfig implements WebMvcConfigurer {

    @Autowired
    private SBInterceptor sbInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sbInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("");
    }
}
