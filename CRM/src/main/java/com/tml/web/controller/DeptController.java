package com.tml.web.controller;

import com.tml.exception.DeleteException;
import com.tml.exception.InsertException;
import com.tml.exception.UpdateException;
import com.tml.pojo.Dept;
import com.tml.pojo.Value;
import com.tml.pojo.msg.Result;
import com.tml.service.DeptService;
import com.tml.service.ValueService;
import com.tml.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.ValueExp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dept")
@ResponseBody
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping
    public List<Dept> list(){
        return deptService.getAll();
    }

    @GetMapping("{id}")
    public Dept get(@PathVariable String id){
        System.out.println(id);
        return deptService.get(id);
    }



    @PostMapping
    private Map<String,Object> add(Dept dept){
        dept.setId(UUIDUtil.getUUID());
        int insert = deptService.insert(dept);
        if(insert<1){
            throw new InsertException("添加失败!请检查数据是否正确");
        }
        return Result.MSG;
    }

    @DeleteMapping("{ids}")
    public Map<String,Object> delete(@PathVariable String ids){
        String[] id = ids.split(",");
        int delete = deptService.delete(id);
        if (delete<1){
            throw new DeleteException("删除失败!请检查要删除数据的真实性!");
        }
        return Result.MSG;
    }



    @PutMapping
    public Map<String,Object> update(Dept dept){
        int update = deptService.update(dept);
        if(update<1){
            throw new UpdateException("数据更新失败!请检查数据的正确性!!");
        }
        return Result.MSG;
    }
}
