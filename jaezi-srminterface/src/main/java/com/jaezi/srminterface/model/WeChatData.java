package com.jaezi.srminterface.model;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 微信模板配置类
 */

public class WeChatData {
    /**
     * 模板标题
     */
    private WeChatFormat title;

    public WeChatFormat getTitle() {
        return title;
    }

    public void setTitle(WeChatFormat title) {
        this.title = title;
    }
}
