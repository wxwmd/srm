package com.jaezi.info.model;

import com.jaezi.common.base.BaseModel;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 消息信息实体类
 */

public class Info extends BaseModel {
    /**
     * 消息信息ID
     */
    private Integer Id;
    /**
     * 消息回复信息关联ID
     */
    private Integer replyId;
    /**
     * 发件人
     */
    private String sender;
    /**
     * 收件人
     */
    private String recipient;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 状态 0：暂存 1：已发送 2：已删除
     */
    private Integer status;
    /**
     * 状态 0：暂存 1：已发送 2：已删除
     */
    private Integer senderStatus;
    /**
     * 读取状态 0：暂存 1：已读 2：未读
     */
    private Integer senderReadStatus;
    /**
     * 状态 0：暂存 1：已发送 2：已删除
     */
    private Integer recipientStatus;
    /**
     * 读取状态 0：暂存 1：已读 2：未读
     */
    private Integer recipientReadStatus;
    /**
     * 附件信息集合
     */
    private String accessoryCollection;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 发布时间
     */
    private Long createTime;
    /**
     * 审核状态
     */
    private String auditStatus;

    private List<InfoStaff> infoStaffList;

    /**
     * 备注
     */
    private String remark;

    /**
     * 二级界面文件地址
     */
    private String documentUrl;

    /**
     * 二级界面文件名称
     */
    private String documentName;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public List<InfoStaff> getInfoStaffList() {
        return infoStaffList;
    }

    public void setInfoStaffList(List<InfoStaff> infoStaffList) {
        this.infoStaffList = infoStaffList;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSenderStatus() {
        return senderStatus;
    }

    public void setSenderStatus(Integer senderStatus) {
        this.senderStatus = senderStatus;
    }

    public Integer getSenderReadStatus() {
        return senderReadStatus;
    }

    public void setSenderReadStatus(Integer senderReadStatus) {
        this.senderReadStatus = senderReadStatus;
    }

    public Integer getRecipientStatus() {
        return recipientStatus;
    }

    public void setRecipientStatus(Integer recipientStatus) {
        this.recipientStatus = recipientStatus;
    }

    public Integer getRecipientReadStatus() {
        return recipientReadStatus;
    }

    public void setRecipientReadStatus(Integer recipientReadStatus) {
        this.recipientReadStatus = recipientReadStatus;
    }

    public String getAccessoryCollection() {
        return accessoryCollection;
    }

    public void setAccessoryCollection(String accessoryCollection) {
        this.accessoryCollection = accessoryCollection;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
