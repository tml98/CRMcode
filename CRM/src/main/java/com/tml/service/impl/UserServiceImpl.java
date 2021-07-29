package com.tml.service.impl;

import com.tml.exception.LoginException;
import com.tml.mapper.UserMapper;
import com.tml.pojo.User;
import com.tml.service.UserService;
import com.tml.utils.DateTimeUtil;
import com.tml.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(String username, String password,String ip) {
        password= MD5Util.getMD5(password);
        User user = userMapper.getUser(username, password);
        if (user==null){
            throw new LoginException("账号不存在或密码错误!!");
        }
        /*判断账号是否过期*/
        String expireTime = user.getExpireTime();
        if(expireTime!=null && !"".equals(expireTime)){
            long now = System.currentTimeMillis();
            try {
                Date parse = DateTimeUtil.SDF19.parse(expireTime);
                if(now>parse.getTime()){
                    throw new LoginException("账号已经过期!");
                }
            } catch (ParseException e) {
                throw new LoginException("账号已经过期!");
            }
        }

        /*判断账号是否被锁定*/
        String lockStatus = user.getLockStatus();
        if (lockStatus.equals("0")){
            throw new LoginException("抱歉!您的账号被锁定,暂时无法登陆!");
        }

        /*判断ip是否允许登录*/
        String allowIps = user.getAllowIps();
        if(allowIps!=null && !"".equals(allowIps)){
            String[] ips = allowIps.split(",");
            boolean flag=false;
            for (String s : ips) {
                if(s.equals(ip)){
                    flag=true;
                }
            }
            if (!flag){
                throw new LoginException("该ip不允许登录!!");
            }
        }
        return user;
    }

    @Override
    public User getUserAutoLogin(String username, String password, String ip) {
        User user = userMapper.getUser(username, password);
        if (user==null){
            return null;
        }
        /*判断账号是否过期*/
        String expireTime = user.getExpireTime();
        if(expireTime!=null && !"".equals(expireTime)){
            long now = System.currentTimeMillis();
            try {
                Date parse = DateTimeUtil.SDF19.parse(expireTime);
                if(now>parse.getTime()){
                    return null;
                }
            } catch (ParseException e) {
                return null;
            }
        }
        /*判断账号是否被锁定*/
        String lockStatus = user.getLockStatus();
        if (lockStatus.equals("0")){
            return null;
        }
        /*判断ip是否允许登录*/
        String allowIps = user.getAllowIps();
        if(allowIps!=null && !"".equals(allowIps)){
            String[] ips = allowIps.split(",");
            boolean flag=false;
            for (String s : ips) {
                if(s.equals(ip)){
                    flag=true;
                }
            }
            if (!flag){
                return null;
            }
        }
        return user;
    }

    @Override
    public List<String> getAll() {
        return userMapper.getAll();
    }
}
