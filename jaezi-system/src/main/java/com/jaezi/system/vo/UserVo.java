package com.jaezi.system.vo;

import com.jaezi.system.model.User;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2019/5/27 16:28
 * @description 用户扩展类
 */
public class UserVo extends User {

    private String roleName;      //角色名称

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
