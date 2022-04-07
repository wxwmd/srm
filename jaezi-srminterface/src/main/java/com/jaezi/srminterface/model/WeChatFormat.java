package com.jaezi.srminterface.model;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 微信模板配置类
 */


public class WeChatFormat {
    /**
     * 模板值
     */
    private String value;
    /**
     * 模板颜色
     */
    private String color = "#173177";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
