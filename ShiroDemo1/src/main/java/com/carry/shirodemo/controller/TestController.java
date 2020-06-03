package com.carry.shirodemo.controller;

import com.carry.shirodemo.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "/unauthorized";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        return "edit success";
    }

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @RequestMapping("/logout")
    public String logout(){
        //移除这个session
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            subject.logout();
        }
        return "/logout";
    }

    @RequestMapping("/admin")
    //这个注释的意思是返回数据 不用跳转页面
    @ResponseBody
    public String admin(){
        return "admin success";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        try{
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(token);

            User user = (User)subject.getPrincipal();
            session.setAttribute("user",user);
            return "/index";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "/login";
        }
    }
}
