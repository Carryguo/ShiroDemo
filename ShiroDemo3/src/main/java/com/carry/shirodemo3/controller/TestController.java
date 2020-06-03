package com.carry.shirodemo3.controller;

import com.carry.shirodemo3.bean.Msg;
import com.carry.shirodemo3.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("/login")
    public Msg login(){
        return Msg.failure().setCode(401).setMessage("未登录");
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "/unauthorized";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
       User user = (User)SecurityUtils.getSubject().getPrincipal();
        System.out.println(user.getUsername());
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

    @PostMapping("/loginUser")
        public Msg loginUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session)
    {
//        System.out.println("username:"+username);
//        System.out.println("");
        String sessionId = null;
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            sessionId = (String) subject.getSession().getId();
        }catch (AuthenticationException e){
            e.printStackTrace();
            return Msg.failure("账号或密码错误");
        }catch (AuthorizationException e){
            e.printStackTrace();
            return Msg.failure("没有权限");
        }
        Map<String,String> map = new HashMap<>();
        System.out.println(sessionId);
        map.put("token",sessionId);
        return Msg.success().setData(map);
    }
}
