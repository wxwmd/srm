package com.jaezi.synergia.dto;

import com.jaezi.synergia.model.FrequentlyUsedData;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/9/1 15:47
 * @description
 */
public class FrequentlyUsedDataDto extends FrequentlyUsedData {

    /**
     * 可见人员集合
     */
    private List<Integer> visiblePersonList;
    
    public List<Integer> getVisiblePersonList() {
        return visiblePersonList;
    }

    public void setVisiblePersonList(List<Integer> visiblePersonList) {
        this.visiblePersonList = visiblePersonList;
    }
}
