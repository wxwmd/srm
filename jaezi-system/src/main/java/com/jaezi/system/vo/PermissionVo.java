package com.jaezi.system.vo;

import com.jaezi.system.model.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/5/19 19:13
 * @description
 */
public class PermissionVo extends Permission {

    private List<PermissionVo> children;

    public List<PermissionVo> getChildren() {
        return children == null ? new ArrayList<>() : children;
    }

    public void setChildren(List<PermissionVo> children) {
        this.children = children;
    }
}
