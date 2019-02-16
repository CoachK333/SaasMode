package com.boot.data;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 98548
 * @create 2019-01-21 17:58
 * @description
 */
public class DataFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("服务启动,Filter初始化...");

    }

    @Override
    public void destroy() {
        System.out.println("服务关闭,Filter销毁...");
    }
}
