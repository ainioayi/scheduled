package com.bobo.threadlocal;

import com.bobo.threadlocal.pojo.OnlineUser;
import com.bobo.threadlocal.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bobo
 * @date 2021/8/11
 * @apiNote session拦截器
 * 普及：HandlerInterceptor
 *
 * preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
 * postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView （这个博主就基本不怎么用了）；
 * afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
 */
@Component
@Slf4j
public class SessionInterceptorConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        //如果用户不为空，则通过
        if (user != null) {
            //将在线用户存入线程本地ThreadLocal中
            OnlineUser onlineUser = new OnlineUser(user.getId(), user.getName());
            ThreadLocalHolder.set(onlineUser);
            return true;
        } else {
            log.info(">>>session过期, 跳至登录页");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //将线程本地ThreadLocal中的在线用户移除（需要手动移除，否则会产生内存泄露）
        ThreadLocalHolder.remove();
    }
}
