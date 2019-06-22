package com.boot.data.interceptor;

import cn.hutool.extra.emoji.EmojiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author 98548
 * @create 2019-06-17 11:49
 * @description
 */
@Component
public class EmojiInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<String> list;
        for (String s : parameterMap.keySet()) {
            String[] strings = parameterMap.get(s);
            for (String string : strings) {
                list = EmojiUtil.extractEmojis(string);
                if (list != null && !list.isEmpty()) {
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().print("暂不支持表情!");
                    return false;
                }
            }
        }
        return true;
    }
}
