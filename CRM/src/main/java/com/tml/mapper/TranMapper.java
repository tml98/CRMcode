package com.tml.mapper;

import com.tml.pojo.Page;
import com.tml.pojo.Tran;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TranMapper {

    List<Tran> getBySome(@Param("map") Map map,
                         @Param("startIndex") Integer startIndex,
                         @Param("rowsPerPage") Integer rowsPerPage);

    int getCount(@Param("map") Map map);
}
