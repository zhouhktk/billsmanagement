package com.zfnotes.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:zhoufeng
 * @Date:2020/3/22
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user!=null){
            return true;
        }else {
            request.setAttribute("msg", "请先登陆");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }

    }
}
