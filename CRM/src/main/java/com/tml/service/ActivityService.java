package com.tml.service;

import com.tml.pojo.Activity;
import com.tml.pojo.ActivityRemark;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ActivityService extends BaseService{

    int insert(Map<String,Object> activity);

    int insertList(List<Activity> activities);

    int delete(Serializable[] ids);

    int update(Map<String,Object> activity);

    List<Activity> getBySome(Map map);

    Map<String,Object> get(Serializable id);
}
