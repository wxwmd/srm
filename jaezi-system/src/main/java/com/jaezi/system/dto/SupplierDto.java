package com.jaezi.system.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.system.model.Supplier;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/21 15:39
 * @description 供应商的数据传输对象
 */

public class SupplierDto extends Supplier {
    /**
     * 用户名
     */
    @ExcelProperty("供应商编码")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 状态 0：正常 1：禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 最后登陆时间
     */
    private Long lastLoginTime;

    /**
     * 用户类型 用户类型 0：企业用户 1：供应商用户 2: 管理员
     */
    private Integer userType;

    /**
     * 真实姓名
     */
    @ExcelProperty("供应商名称")
    private String realName;

    /**
     * 用户token
     */
    private String token;

    /**
     * 供应商限额
     */
    private BigDecimal quota;

    /**
     * 采购员工号
     */
    private String buyerNumber;

    /**
     * 电话
     */
    private String phone;

    /**
     * 公司名称
     */
    private String companyName;

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public void setBuyerNumber(String buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
