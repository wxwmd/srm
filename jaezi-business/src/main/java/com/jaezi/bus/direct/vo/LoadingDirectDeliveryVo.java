package com.jaezi.bus.direct.vo;

import com.jaezi.bus.direct.model.LoadingDirectDelivery;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/17 16:26
 * @description
 * 直达装车单vo类
 */
public class LoadingDirectDeliveryVo extends LoadingDirectDelivery {
    /**
     * 供应商名称
     */
    private String supplierName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
