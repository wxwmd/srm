package com.jaezi.synergia.dto;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3 10:31
 * @description
 * 设置可见类型是用来接收的对象
 */
public class VisibleDto {

    /**
     * 资料Id集合
     */
    private List<Integer> dataIdList;

    /**
     * 可见人员集合
     */
    private List<Integer> visiblePersonList;

    /**
     * 可见类型（0：部分供应商，1：全部供应商，2：企业）
     */
    private Integer visibleType;

    public List<Integer> getDataIdList() {
        return dataIdList;
    }

    public void setDataIdList(List<Integer> dataIdList) {
        this.dataIdList = dataIdList;
    }

    public List<Integer> getVisiblePersonList() {
        return visiblePersonList;
    }

    public void setVisiblePersonList(List<Integer> visiblePersonList) {
        this.visiblePersonList = visiblePersonList;
    }

    public Integer getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(Integer visibleType) {
        this.visibleType = visibleType;
    }
}
