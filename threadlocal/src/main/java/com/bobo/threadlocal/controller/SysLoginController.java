package com.bobo.threadlocal.controller;

import com.bobo.threadlocal.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bobo
 * @date 2021/8/11
 * @apiNote
 */
@RestController
public class SysLoginController {

    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, User user) {
        User userSession = (User) request.getSession().getAttribute("user");
        //用户未登录
        if(userSession == null){
            //校验：名字是否空、用户是否存在、密码是否空、密码是否正确
            if (user.getPassword()!=null){
                //执行登陆后台的逻辑
                request.getSession().setAttribute("user",user);
                return "登录成功";
            }else{
                return "密码不正确，请重新输入";
            }
        }else{
            return "登录成功";
        }
    }

    @PostMapping(value = "logout")
    public String logoutApi(HttpServletRequest request) {
        //销毁session
        request.getSession().invalidate();
        return "退出登录成功";
    }

}
