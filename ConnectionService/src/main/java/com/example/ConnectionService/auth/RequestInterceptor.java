package com.example.ConnectionService.auth;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextHolder.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getRequestURI();

        if (path.contains("/signup") || path.contains("/login") || path.contains("/auth")) {
            return true;
        }

        String userId=request.getHeader("X-User-Id");
        AuthContextHolder.setCurUserId(Long.valueOf(userId));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
