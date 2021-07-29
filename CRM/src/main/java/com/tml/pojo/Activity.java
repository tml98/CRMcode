package com.tml.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Activity {
    private String id;
    private String owner;
    private String name;
    private String startDate;
    private String endDate;
    private String cost;
    private String description;
    private String createBy;
    private String createTime;
    private String editBy;
    private String editTime;

    private List<ActivityRemark> activityRemarks;
}
