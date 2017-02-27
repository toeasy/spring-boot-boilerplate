package com.xn.mars.interceptor;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求计时器，监控记录每次请求的花费时间
 * author:Liang.qinjie
 * on 2017-02-17 11:26
 */
public class TimeInterceptor implements HandlerInterceptor {

    private NamedThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();
        long consumeTime = endTime - beginTime;
        if (consumeTime > 500) {//请求处理时间超过500毫秒的为慢请求，系统记录下来

            System.out.println(
                    String.format("请求[%s]的处理时间为[%d]ms", httpServletRequest.getRequestURI(), consumeTime));
        }
    }
}
