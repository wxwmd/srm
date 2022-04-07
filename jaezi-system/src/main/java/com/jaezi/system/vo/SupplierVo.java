package com.jaezi.system.vo;

import com.jaezi.system.model.Supplier;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 供应商扩展类
 */

public class SupplierVo extends Supplier {

    private String phone;

    private String username;

    private BigDecimal quota;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }
}
