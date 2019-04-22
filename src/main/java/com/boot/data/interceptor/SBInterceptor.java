package com.boot.data.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 98548
 * @create 2019-04-03 11:30
 * @description
 */
@Component
public class SBInterceptor extends HandlerInterceptorAdapter {

    int i = 0;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getSession().setAttribute("t", ++i);

        System.out.println("被傻逼拦截了...");
        System.out.println("然后又放开了...");
        System.out.println("当前用户有:【" + i + "】个");
        return true;
    }
}
