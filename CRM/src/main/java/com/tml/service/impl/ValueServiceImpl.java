package com.tml.service.impl;

import com.tml.mapper.ValueMapper;
import com.tml.pojo.Value;
import com.tml.service.ValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service
public class ValueServiceImpl implements ValueService {

    @Resource
    private ValueMapper valueMapper;

    @Override
    public int insert(Value value) {
        return valueMapper.insert(value);
    }

    @Override
    public int delete(Serializable... ids) {
        return valueMapper.delete(ids);
    }

    @Override
    public int update(Value value) {
        return valueMapper.update(value);
    }

    @Override
    public Value get(Serializable id) {
        return valueMapper.get(id);
    }

    @Override
    public List<Value> getTypeCode(Serializable[] code) {
        return valueMapper.getTypeCode(code);
    }

    @Override
    public List<Value> getAll() {
        return valueMapper.getAll();
    }
}
