package com.tml.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CommonMapper {

    int insert(@Param("tableName") String tableName,
               @Param("data") Map<String,Object> map);

    int update(@Param("tableName") String tableName,
               @Param("data") Map<String,Object> map);

    int delete(@Param("tableName") String tableName,
               @Param("ids")Serializable[] ids);

    List<Map<String,Object>> getAll(@Param("tableName") String tableName);

    Map<String,Object> get(@Param("tableName") String tableName,
                           @Param("id") Serializable id);
}
