package com.tml.pojo;

import lombok.Data;

@Data
public class ActivityRemark {
    private String id;
    private String createBy;
    private String noteContent;
    private String createTime;
    private String editBy;
    private String editTime;
    private String editFlag;
    private String marketingActivitiesId;
}
