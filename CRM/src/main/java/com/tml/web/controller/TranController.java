package com.tml.web.controller;

import com.tml.pojo.Page;
import com.tml.service.TranService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("tran")
public class TranController {
    @Resource
    private TranService tranService;

    @GetMapping("listPage")
    public Page getPage(Map map,Page page){
        tranService.getBySome(map, page);
       return page;
    }
}
