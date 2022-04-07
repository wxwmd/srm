package com.jaezi.srminterface.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 用户微信绑定信息实体类
 */

public class WeChatUser extends BaseModel {
    /**
     * 关联ID
     */
    private Integer id;
    /**
     * 微信用户唯一标识
     */
    private String openId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 微信昵称
     */
    private String nickName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
