package com.example.test1.interceptor;

import com.example.test1.annoation.UserLoginToken;
import com.example.test1.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class TokenConfirmInterceptor implements HandlerInterceptor {
    @Resource
    RedisUtil redisUtil;

    static Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod)handler;
            Method method=handlerMethod.getMethod();
            if (method.isAnnotationPresent(UserLoginToken.class)){

                String token=request.getHeader("loginUserToken")==null?"":request.getHeader("loginUserToken");
                String id=request.getHeader("loginUserId")==null?"":request.getHeader("loginUserId");

                logger.info("loginUserId:{}",id);
                logger.info("loginUserToken:{}",token);
                if ("".equals(token) || "".equals(id) || !redisUtil.count(id,token)){
                    response.sendRedirect("/user/noToken");
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
