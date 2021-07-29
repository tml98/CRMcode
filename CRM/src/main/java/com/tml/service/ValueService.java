package com.tml.service;

import com.tml.pojo.Value;

import java.io.Serializable;
import java.util.List;

public interface ValueService {
    int insert(Value value);

    int delete(Serializable...ids);

    int update(Value value);

    Value get(Serializable id);

    List<Value> getTypeCode(Serializable[] code);

    List<Value> getAll();
}
