package com.boot.data.saas.config;

import com.boot.data.saas.tenant.TenantInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebSaasConfig implements WebMvcConfigurer {

    @Autowired
    private TenantInterceptor tenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("注册<<多租户>> 域名拦截器");
        registry.addInterceptor(tenantInterceptor).addPathPatterns("/**");
    }
}
