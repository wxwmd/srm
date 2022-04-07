package com.jaezi.system.vo;

import com.jaezi.system.model.Role;

import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/10/23 11:49
 * @description
 */
public class RoleVo extends Role {

    private List<Integer> menuIds;

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
