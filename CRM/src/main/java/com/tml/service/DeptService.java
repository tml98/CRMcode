package com.tml.service;

import com.tml.pojo.Dept;
import com.tml.pojo.Type;

import java.io.Serializable;
import java.util.List;

public interface DeptService {

    int insert(Dept dept);

    int delete(Serializable[] ids);

    int update(Dept dept);

    Dept get(Serializable id);

    List<Dept> getAll();
}
