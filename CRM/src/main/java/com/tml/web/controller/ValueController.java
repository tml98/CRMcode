package com.tml.web.controller;

import com.tml.exception.DeleteException;
import com.tml.exception.InsertException;
import com.tml.exception.UpdateException;
import com.tml.pojo.Value;
import com.tml.pojo.msg.Result;
import com.tml.service.ValueService;
import com.tml.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("value")
@ResponseBody
public class ValueController {

    @Resource
    private ValueService valueService;

    @GetMapping
    public List<Value> list() {
        return valueService.getAll();
    }

    @GetMapping("{id}")
    public Value get(@PathVariable String id) {
        return valueService.get(id);
    }


    @PostMapping
    private Map<String, Object> add(Value value) {
        value.setId(UUIDUtil.getUUID());
        int insert = valueService.insert(value);
        if (insert < 1) {
            throw new InsertException("添加失败!请检查数据是否正确");
        }
        return Result.MSG;
    }

    @DeleteMapping("{id}")
    public Map<String, Object> delete(@PathVariable String id) {
        String[] ids = id.split(",");
        int delete = valueService.delete(ids);
        if (delete < 1) {
            throw new DeleteException("删除失败!请检查要删除数据的真实性!");
        }
        return Result.MSG;
    }


    @PutMapping
    public Map<String, Object> update(Value value) {
        int update = valueService.update(value);
        if (update < 1) {
            throw new UpdateException("数据更新失败!请检查数据的正确性!!");
        }
        return Result.MSG;
    }
}
