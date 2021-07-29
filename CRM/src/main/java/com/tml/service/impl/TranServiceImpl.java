package com.tml.service.impl;

import com.tml.mapper.TranMapper;
import com.tml.pojo.Clue;
import com.tml.pojo.Page;
import com.tml.pojo.Tran;
import com.tml.service.TranService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TranServiceImpl implements TranService {

    @Resource
    private TranMapper tranMapper;

    @Override
    public Page getBySome(Map map, Page page) {
        Integer currentPage = page.getCurrentPage();//当前页码
        Integer rowsPerPage = page.getRowsPerPage();//一页显示的数据
        Integer totalRows = tranMapper.getCount(map);//总记录数
        Integer startIndex = (currentPage - 1) * rowsPerPage;//数据开始查询的索引
        Integer totalPages = (totalRows - 1) / rowsPerPage + 1;//总页数
        List<Tran> trans = tranMapper.getBySome(map, startIndex, rowsPerPage);
        page.setTotalRows(totalRows);
        page.setTotalPages(totalPages);
        page.setData(trans);
        return page;
    }
}
