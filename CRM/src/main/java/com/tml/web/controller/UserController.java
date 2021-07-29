package com.tml.web.controller;

import com.tml.pojo.User;
import com.tml.pojo.msg.Result;
import com.tml.service.UserService;
import com.tml.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
@ResponseBody
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("login.do")
    public Map<String,Object> login(String username, String password, boolean autoLogin,
                                    HttpServletRequest request, HttpServletResponse response){
        String ip = request.getRemoteAddr();
        User user = userService.getUser(username, password, ip);
        request.getSession().setAttribute("user",user);
        if(autoLogin){
            int time=3600*24*3;
            Cookie cookie1 = new Cookie("username", username);
            cookie1.setMaxAge(time);
            cookie1.setPath("/");
            password= MD5Util.getMD5(password);
            Cookie cookie2 = new Cookie("password", password);
            cookie2.setMaxAge(time);
            cookie2.setPath("/");
            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }
        return Result.MSG;
    }

    @RequestMapping("/logout.do")
    public Map<String,Object> logout(HttpSession session,HttpServletResponse response){
        session.removeAttribute("user");

        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return Result.MSG;
    }

    @GetMapping
    public List<String> list(){
        return userService.getAll();
    }
}
