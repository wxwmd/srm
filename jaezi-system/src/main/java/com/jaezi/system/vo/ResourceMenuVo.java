package com.jaezi.system.vo;

import com.jaezi.system.model.ResourceMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/8/27  17:20
 * @description 菜单扩展类
 */
public class ResourceMenuVo extends ResourceMenu {

    private String permissionName;
    private List<ResourceMenuVo> children = new ArrayList<>();   //子菜单列表

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<ResourceMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceMenuVo> children) {
        this.children = children;
    }

}
