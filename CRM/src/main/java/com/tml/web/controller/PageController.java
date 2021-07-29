package com.tml.web.controller;

import com.tml.pojo.User;
import com.tml.service.UserService;
import com.tml.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @Resource
    private UserService userService;

    /*根路径请求,若存在cookie,则进入主页面*/
    @RequestMapping("/")
    public String toLogin(@CookieValue(name = "username",required = false)String username,
                          @CookieValue(name = "password",required = false)String password,
                          HttpServletRequest request){
        if(StringUtils.isNoneBlank(username,password)){
            User user = userService.getUserAutoLogin(username, password, request.getRemoteAddr());
            if(user!=null){
                request.getSession().setAttribute("user",user);
                return "redirect:/workbench/index.html";
            }
        }
        return "index";
    }

    @RequestMapping("/**/*.html")
    public String page(HttpServletRequest request){
        String uri = request.getRequestURI();
        return uri.substring(1,uri.indexOf("."));
    }
}
