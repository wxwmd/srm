package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.Permission;
import com.jaezi.system.vo.PermissionVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/5/19 19:13
 * @description
 */
@Repository
public interface PermissionDao extends BaseDao<Permission, PermissionVo> {

    /**
     * 根据权限名称获取获取信息
     * @param permissionName 权限名称
     * @return 权限对象
     */
    Permission getPermissionByName(String permissionName);

    /**
     * 根据角色id 获取权限id集合
     * @param roleId 角色id
     * @return 权限id集合
     */
    List<Integer> getPermissionIdsByRoleId(Integer roleId);
}
