package com.tml.utils;

import com.tml.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpSession;

public class ControllerUtil {

    public static void initInsert(Object o, HttpSession session){
        User user = (User) session.getAttribute("user");
        String createBy=user.getLoginAct()+"|"+user.getName();
        String createTime=DateTimeUtil.getSysTime();
        try {
            BeanUtils.setProperty(o,"id",UUIDUtil.getUUID());
            BeanUtils.setProperty(o,"createBy",createBy);
            BeanUtils.setProperty(o,"createTime",createTime);
            BeanUtils.setProperty(o,"editBy",createBy);
            BeanUtils.setProperty(o,"editTime",createTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void initUpdate(Object o, HttpSession session){
        User user = (User) session.getAttribute("user");
        String editBy=user.getLoginAct()+"|"+user.getName();
        String editTime=DateTimeUtil.getSysTime();
        try {
            BeanUtils.setProperty(o,"editBy",editBy);
            BeanUtils.setProperty(o,"editTime",editTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
