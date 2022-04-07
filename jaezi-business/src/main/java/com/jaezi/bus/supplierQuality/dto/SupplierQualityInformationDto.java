package com.jaezi.bus.supplierQuality.dto;

import com.jaezi.bus.supplierQuality.model.SupplierQualityInformation;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 17:23
 * @description
 */
public class SupplierQualityInformationDto extends SupplierQualityInformation {

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
