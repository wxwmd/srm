package com.jaezi.system.dto;

import com.jaezi.system.model.Role;

import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/8/28  14:29
 * @description 角色的数据传输对象
 */
public class RoleDto extends Role {

    private Integer status;
    private List<Integer> permissionIds;

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
