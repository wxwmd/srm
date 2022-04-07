package com.jaezi.common.bean;

import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2018/5/18 11:36
 * @description 分页数据的返回对象封装
 */

public class DataGrid<T> {
    private Long total;          //总记录数
    private List<T> records;        //列表

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
