package com.tml.service;

import com.tml.pojo.Clue;
import com.tml.pojo.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ClueService {

    Page getPage(Map map, Page page);

    Clue getOne(Serializable id);

    int deleteCAR(String clueId,String activityId);
}
