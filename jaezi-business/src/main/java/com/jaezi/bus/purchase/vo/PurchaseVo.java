package com.jaezi.bus.purchase.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.jaezi.bus.purchase.model.Purchase;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 采购订单扩展类
 */

public class PurchaseVo extends Purchase {

    @ExcelIgnore
    private String colorStatus;

    public String getColorStatus() {
        return colorStatus;
    }

    public void setColorStatus(String colorStatus) {
        this.colorStatus = colorStatus;
    }
}
