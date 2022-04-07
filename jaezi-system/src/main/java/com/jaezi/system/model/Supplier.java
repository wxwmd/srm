package com.jaezi.system.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/21 15:39
 * @description 供应商实体类
 */

public class Supplier extends BaseModel {
    private Integer supplierId;             //供应商ID
    private Integer userId;                 //用户ID
    private Integer examineStatus;          //审核状态 '0：待审核 1：审核通过 2:审核不通过',
    private Integer problem;                //问题
    private String answer;                  //答案
    @ExcelProperty("供应商地址")
    private String companyName;             //公司名称
    private String companyAddress;          //公司地址
    private String companyWebsite;          //公司网址

    private String companyFax;              //公司传真
    private String technicalDirector;       //技术负责人
    private String technicalDirectorPhone;  //技术负责人联系方式
    private String financeDirector;         //财务负责人
    private String financeDirectorPhone;    //财务负责人联系方式
    private String saleDirector;            //销售负责人
    private String saleDirectorPhone;       //销售负责人联系方式
    private String industryExperience;      //行业经验（年）
    private Integer mainMaterials;          //主营物资
    private String otherMainMaterials;      //其他主营物资
    private Integer proposedSupplies;       //拟供物资
    private String otherProposedSupplies;   //其他拟供物资
    private String currentQualitySystem;   //现行质量体系
    private String mainCompetitors;         //主要竞争对手
    private String nowCompanyMainCustomers; //目前公司主要客户
    private String certificateUrl;          //证书url
    private String topSix;                  //拟供物资行业前六名列举
    private String companyEmail;            //电子邮箱
    private String proposedSuppliesTop;     //拟供物资排名
    private String supplierHaulCycle;     //运输周期

    public String getSupplierHaulCycle() {
        return supplierHaulCycle;
    }
    public void setSupplierHaulCycle(String supplierHaulCycle) {
        this.supplierHaulCycle = supplierHaulCycle;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getProposedSuppliesTop() {
        return proposedSuppliesTop;
    }

    public void setProposedSuppliesTop(String proposedSuppliesTop) {
        this.proposedSuppliesTop = proposedSuppliesTop;
    }

    public String getTopSix() {
        return topSix;
    }

    public void setTopSix(String topSix) {
        this.topSix = topSix;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(Integer examineStatus) {
        this.examineStatus = examineStatus;
    }

    public Integer getProblem() {
        return problem;
    }

    public void setProblem(Integer problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getTechnicalDirector() {
        return technicalDirector;
    }

    public void setTechnicalDirector(String technicalDirector) {
        this.technicalDirector = technicalDirector;
    }

    public String getTechnicalDirectorPhone() {
        return technicalDirectorPhone;
    }

    public void setTechnicalDirectorPhone(String technicalDirectorPhone) {
        this.technicalDirectorPhone = technicalDirectorPhone;
    }

    public String getFinanceDirector() {
        return financeDirector;
    }

    public void setFinanceDirector(String financeDirector) {
        this.financeDirector = financeDirector;
    }

    public String getFinanceDirectorPhone() {
        return financeDirectorPhone;
    }

    public void setFinanceDirectorPhone(String financeDirectorPhone) {
        this.financeDirectorPhone = financeDirectorPhone;
    }

    public String getSaleDirector() {
        return saleDirector;
    }

    public void setSaleDirector(String saleDirector) {
        this.saleDirector = saleDirector;
    }

    public String getSaleDirectorPhone() {
        return saleDirectorPhone;
    }

    public void setSaleDirectorPhone(String saleDirectorPhone) {
        this.saleDirectorPhone = saleDirectorPhone;
    }

    public String getIndustryExperience() {
        return industryExperience;
    }

    public void setIndustryExperience(String industryExperience) {
        this.industryExperience = industryExperience;
    }

    public Integer getMainMaterials() {
        return mainMaterials;
    }

    public void setMainMaterials(Integer mainMaterials) {
        this.mainMaterials = mainMaterials;
    }

    public String getOtherMainMaterials() {
        return otherMainMaterials;
    }

    public void setOtherMainMaterials(String otherMainMaterials) {
        this.otherMainMaterials = otherMainMaterials;
    }

    public Integer getProposedSupplies() {
        return proposedSupplies;
    }

    public void setProposedSupplies(Integer proposedSupplies) {
        this.proposedSupplies = proposedSupplies;
    }

    public String getOtherProposedSupplies() {
        return otherProposedSupplies;
    }

    public void setOtherProposedSupplies(String otherProposedSupplies) {
        this.otherProposedSupplies = otherProposedSupplies;
    }

    public String getCurrentQualitySystem() {
        return currentQualitySystem;
    }

    public void setCurrentQualitySystem(String currentQualitySystem) {
        this.currentQualitySystem = currentQualitySystem;
    }

    public String getMainCompetitors() {
        return mainCompetitors;
    }

    public void setMainCompetitors(String mainCompetitors) {
        this.mainCompetitors = mainCompetitors;
    }

    public String getNowCompanyMainCustomers() {
        return nowCompanyMainCustomers;
    }

    public void setNowCompanyMainCustomers(String nowCompanyMainCustomers) {
        this.nowCompanyMainCustomers = nowCompanyMainCustomers;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }
}
