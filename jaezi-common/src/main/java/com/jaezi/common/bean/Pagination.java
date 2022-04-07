package com.jaezi.common.bean;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2018/5/18 11:36
 * @description 分页对象的封装
 */

public class Pagination {
    private Integer page;           //当前页
    private Integer limit;           //每页大小

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer skip(){
        int skip = (page - 1) * limit;
        return Math.max(skip, 0);
    }
}
