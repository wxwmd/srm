package com.jaezi.system.vo;

import com.jaezi.system.model.ResourceApi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/07/15 15:35
 * @description
 */
public class ResourceApiVo extends ResourceApi {

    private String permissionName;
    private Integer pid;
    private List<ResourceApiVo> children = new ArrayList<>();

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<ResourceApiVo> getChildren() {
        return children == null ? new ArrayList<>() : children;
    }

    public void setChildren(List<ResourceApiVo> children) {
        this.children = children;
    }
}
