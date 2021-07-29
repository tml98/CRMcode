package com.tml.mapper;

import com.tml.pojo.ActivityRemark;

import java.io.Serializable;
import java.util.List;

public interface ActivityRemarkMapper {

    List<ActivityRemark> getRemarks(Serializable marketingActivitiesId);
}
