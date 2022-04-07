package com.jaezi.info.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 消息信息抄送人员实体类
 */

public class InfoStaff extends BaseModel {
    /**
     * 消息关联ID
     */
    private Integer infoId;
    /**
     * 人员
     */
    private String staff;
    /**
     * 状态 0：暂存 1：已发送 2：已删除
     */
    private Integer status;
    /**
     * 读取状态 0：暂存 1：已读 2：未读
     */
    private Integer readStatus;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }
}
