package com.boot.data.config;

import com.boot.data.interceptor.EmojiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 98548
 * @create 2019-06-17 15:20
 * @description
 */
@Configuration
public class EmojiConfig implements WebMvcConfigurer {

    @Autowired
    private EmojiInterceptor emojiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(emojiInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("");
    }
}
