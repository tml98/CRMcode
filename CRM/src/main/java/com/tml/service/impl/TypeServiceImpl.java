package com.tml.service.impl;

import com.tml.mapper.TypeMapper;
import com.tml.pojo.Type;
import com.tml.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    public int insert(Type type) {
        return typeMapper.insert(type);
    }

    @Override
    public int delete(Serializable[] ids) {
        return typeMapper.delete(ids);
    }

    @Override
    public int update(Type type) {
        return typeMapper.update(type);
    }

    @Override
    public Type get(Serializable id) {
        return typeMapper.get(id);
    }

    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }
}
