package com.tml.web.controller;

import com.tml.pojo.Clue;
import com.tml.pojo.Page;
import com.tml.pojo.msg.Result;
import com.tml.service.ClueService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

@RestController
@RequestMapping("clue")
public class ClueController {

    @Resource
    private ClueService clueService;

    @GetMapping("pageList")
    public Page getPage(@RequestParam Map map, Page page){
        clueService.getPage(map,page);
        return page;
    }

    @GetMapping("getOne")
    public Clue getOne(String id){
        return clueService.getOne(id);
    }

    @DeleteMapping("deleteCAR/{clueId}/{activityId}")
    public Map<String,Object> deleteCAR(@PathVariable("clueId") String clueId,
                                        @PathVariable("activityId") String activityId){
        clueService.deleteCAR(clueId, activityId);
        return Result.MSG;
    }
}
