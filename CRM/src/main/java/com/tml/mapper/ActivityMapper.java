package com.tml.mapper;

import com.tml.pojo.Activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ActivityMapper {

    int insert(Activity activity);

    int insertList(List<Activity> activities);

    int delete(Serializable[] ids);

    int update(Activity activity);

    List<Activity> getBySome(Map map);

    Activity get(Serializable id);
}
