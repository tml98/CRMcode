package com.tml.mapper;

import com.tml.pojo.Clue;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ClueMapper {

    Integer getCount(@Param("data") Map map);

    List<Clue> get(@Param("data") Map map,
                   @Param("startIndex") Integer startIndex,
                   @Param("rowsPerPage") Integer rowsPerPage);

    Clue getOne(Serializable id);

    int deleteCAR(@Param("clueId") String clueId,
                  @Param("activityId") String activityId);
}
