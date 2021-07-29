package com.tml.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Page {
    private Integer currentPage = 1;            //页码
    private Integer rowsPerPage = 5;            //每页显示的记录条数
    private Integer maxRowsPerPage = 100;       //每页最多显示的记录条数
    private Integer visiblePageLinks = 5;       //显示几个卡片
    private Integer totalPages;                 //总页数
    private Integer totalRows;                  //总记录数

    private List data;
}
