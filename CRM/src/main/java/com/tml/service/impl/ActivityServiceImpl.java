package com.tml.service.impl;

import com.tml.mapper.ActivityMapper;
import com.tml.mapper.ActivityRemarkMapper;
import com.tml.mapper.CommonMapper;
import com.tml.pojo.Activity;
import com.tml.pojo.ActivityRemark;
import com.tml.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private CommonMapper commonMapper;
    @Resource
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public int insert(Map<String, Object> activity) {
        return commonMapper.insert(tableName.ACT, activity);
    }

    @Override
    public int insertList(List<Activity> activities) {
        return activityMapper.insertList(activities);
    }

    @Override
    public int delete(Serializable[] ids) {
        return commonMapper.delete(tableName.ACT, ids);
    }

    @Override
    public int update(Map<String, Object> activity) {
        return commonMapper.update(tableName.ACT, activity);
    }

    @Override
    public List<Activity> getBySome(Map map) {
        return activityMapper.getBySome(map);
    }

    @Override
    public Map<String, Object> get(Serializable id) {
        List<ActivityRemark> remarks = activityRemarkMapper.getRemarks(id);
        Map<String, Object> map = commonMapper.get(tableName.ACT, id);
        map.put("remarks", remarks);
        return map;
    }

}
