package com.jaezi.bus.finance.model;

import com.jaezi.common.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 标准物资开票实体类
 */

public class StandardGoodsInvoice extends BaseModel {
    /**
     * 标准物资开票ID
     */
    private Integer id;
    /**
     * 采购订单
     */
    private Integer pOrder;
    /**
     * 行项目
     */
    private Integer lineProject;
    /**
     * 物料凭证
     */
    private Integer materialDocument;
    /**
     * 凭证项目
     */
    private Integer certificateProgram;
    /**
     * 物料
     */
    private String material;
    /**
     * 物料描述
     */
    private String materialDescription;
    /**
     * 未开票数量
     */
    private BigDecimal unQuantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPOrder() {
        return pOrder;
    }

    public void setPOrder(Integer pOrder) {
        this.pOrder = pOrder;
    }

    public Integer getLineProject() {
        return lineProject;
    }

    public void setLineProject(Integer lineProject) {
        this.lineProject = lineProject;
    }

    public Integer getMaterialDocument() {
        return materialDocument;
    }

    public void setMaterialDocument(Integer materialDocument) {
        this.materialDocument = materialDocument;
    }

    public Integer getCertificateProgram() {
        return certificateProgram;
    }

    public void setCertificateProgram(Integer certificateProgram) {
        this.certificateProgram = certificateProgram;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public BigDecimal getUnQuantity() {
        return unQuantity;
    }

    public void setUnQuantity(BigDecimal unQuantity) {
        this.unQuantity = unQuantity;
    }
}
