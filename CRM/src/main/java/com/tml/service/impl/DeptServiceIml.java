package com.tml.service.impl;

import com.tml.mapper.DeptMapper;
import com.tml.pojo.Dept;
import com.tml.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service
public class DeptServiceIml implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public int insert(Dept dept) {
        return deptMapper.insert(dept);
    }

    @Override
    public int delete(Serializable[] ids) {
        return deptMapper.delete(ids);
    }

    @Override
    public int update(Dept dept) {
        return deptMapper.update(dept);
    }

    @Override
    public Dept get(Serializable id) {
        return deptMapper.get(id);
    }

    @Override
    public List<Dept> getAll() {
        return deptMapper.getAll();
    }
}
