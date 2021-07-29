package com.tml.service;

import com.tml.pojo.Type;

import java.io.Serializable;
import java.util.List;

public interface TypeService {

    int insert(Type type);

    int delete(Serializable[] ids);

    int update(Type type);

    Type get(Serializable id);

    List<Type> getAll();
}
