package com.tml.web.listener;

import com.tml.pojo.Value;
import com.tml.service.ValueService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class Cache implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        ValueService valueService = context.getBean(ValueService.class);
        List<Value> values = valueService.getAll();

        // 将字典值保存到application作用域，作为缓存
        //sce.getServletContext().setAttribute("dicList", values);
        /*
            list中的实例:id value typeCode
                        1   a       a
                        2   b       a
                        3   c       a
                        4   d       b
                        5   e       b
                        6   f       c

         */
        // 笔试题：将list处理为Map<String, List>, key是typeCode，List是typeCode相同的实例
        Map<String,List<Value>> map = new HashMap<>();
        for (Value value : values) {
            String key = value.getTypeCode();
            /*computeIfAbsent,如果map中没有key对应的值,则将函数计算的值作为可以相关联的值(函数计算的值为空除外)*/
            List<Value> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(value);
        }
        sce.getServletContext().setAttribute("valueMap",map);
    }
}
