package com.tml.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cache")
public class CacheController {
    @GetMapping("get")
    public Object getCache(String type, HttpServletRequest request){
        Map valueMap = (Map) request.getServletContext().getAttribute("valueMap");
        return valueMap.get(type);
    }
}
