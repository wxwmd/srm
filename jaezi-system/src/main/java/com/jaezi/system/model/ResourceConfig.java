package com.jaezi.system.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation 系统资源配置数据实体类
 * @date 2021/07/15 15:37
 * @description
 */
public class ResourceConfig extends BaseModel {

    private Integer id;

    private String config;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
