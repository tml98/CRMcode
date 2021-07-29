package com.tml.web.controller;

import com.tml.exception.DeleteException;
import com.tml.exception.InsertException;
import com.tml.exception.UpdateException;
import com.tml.pojo.Type;
import com.tml.pojo.Value;
import com.tml.pojo.msg.Result;
import com.tml.service.TypeService;
import com.tml.service.ValueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("type")
@ResponseBody
public class TypeController {

    @Resource
    private TypeService typeService;
    @Resource
    private ValueService valueService;

    @GetMapping
    public List<Type> list(){
        return typeService.getAll();
    }

    @GetMapping("{id}")
    public Type get(@PathVariable String id){
        return typeService.get(id);
    }

    @RequestMapping("getTypeCode")
    public List<Value> getTypeCode(String code){
        String[] codes = code.split(",");
        return valueService.getTypeCode(codes);
    }

    @PostMapping
    private Map<String,Object> add(Type type){
        int insert = typeService.insert(type);
        if(insert<1){
            throw new InsertException("添加失败!请检查数据是否正确");
        }
        return Result.MSG;
    }

    @DeleteMapping("{id}")
    public Map<String,Object> delete(@PathVariable String id){
        String[] ids = id.split(",");
        int delete = typeService.delete(ids);
        if (delete<1){
            throw new DeleteException("删除失败!请检查要删除数据的真实性!");
        }
        return Result.MSG;
    }

    @PutMapping
    public Map<String,Object> update(Type type){
        int update = typeService.update(type);
        if(update<1){
            throw new UpdateException("数据更新失败!请检查数据的正确性!!");
        }
        return Result.MSG;
    }
}
