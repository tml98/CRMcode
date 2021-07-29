package com.tml.service.impl;

import com.tml.mapper.ClueMapper;
import com.tml.pojo.Clue;
import com.tml.pojo.Page;
import com.tml.service.ClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class ClueServiceImpl implements ClueService {

    @Resource
    private ClueMapper clueMapper;

    @Override
    public Page getPage(Map map, Page page) {
        Integer currentPage = page.getCurrentPage();                       //当前页码
        Integer rowsPerPage = page.getRowsPerPage();                       //一页显示的数据
        Integer totalRows = clueMapper.getCount(map);                      //总记录数
        Integer startIndex = (currentPage - 1) * rowsPerPage;              //数据开始查询的索引
        Integer totalPages = (totalRows - 1) / rowsPerPage + 1;            //总页数
        List<Clue> clues = clueMapper.get(map, startIndex, rowsPerPage);
        page.setTotalRows(totalRows);
        page.setTotalPages(totalPages);
        page.setData(clues);
        return page;
    }

    @Override
    public Clue getOne(Serializable id) {
        return clueMapper.getOne(id);
    }

    @Override
    public int deleteCAR(String clueId, String activityId) {
        return clueMapper.deleteCAR(clueId, activityId);
    }
}
