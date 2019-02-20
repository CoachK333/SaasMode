package com.boot.data.saas.tenant;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.net.URL;

@Component
@Slf4j
public class TenantInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private DataSourceBasedMultiTenantConnectionProviderImpl dsProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //如果没有初始化先初始化操作
        if (!DataSourceBasedMultiTenantConnectionProviderImpl.init) {
            dsProvider.initTenantDataSource();
        }
        if (null != dsProvider.dataSourcesMtApp) {
            URL url = new URL(request.getRequestURL().toString());
            String domain = url.getHost();
            String domainKey = request.getParameter("usercode");
            if (StringUtils.isBlank(domainKey)) {
                domainKey = MultiTenantConstants.DEFAULT_TENANT_ID;
            }
            log.info("domian:" + domain + "    dominKey:" + domainKey);
            if (dsProvider.dataSourcesMtApp.size() > 0 && dsProvider.dataSourcesMtApp.get(domainKey) != null) {
                request.setAttribute(MultiTenantConstants.CURRENT_TENANT_IDENTIFIER, domainKey);
            } else {
                request.setAttribute(MultiTenantConstants.CURRENT_TENANT_IDENTIFIER, MultiTenantConstants.DEFAULT_TENANT_ID);
            }
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        request.removeAttribute(MultiTenantConstants.CURRENT_TENANT_IDENTIFIER);
    }

}
