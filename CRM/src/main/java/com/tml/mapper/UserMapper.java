package com.tml.mapper;

import com.tml.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User getUser(@Param("username") String username, @Param("password") String password);
    User getUserAutoLogin(@Param("username") String username, @Param("password") String password);

    List<String> getAll();
}
