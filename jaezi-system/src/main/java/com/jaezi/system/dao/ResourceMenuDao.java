package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.ResourceMenu;
import com.jaezi.system.vo.ResourceMenuVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/16 17:12
 * @description 菜单数据访问对象
 */
@Repository
public interface ResourceMenuDao extends BaseDao<ResourceMenu, ResourceMenuVo> {

    /**
     * 根据角色id获取对应的菜单列表
     * @param roleId 角色id
     * @return 菜单列表
     */
    List<ResourceMenuVo> getMenusByRoleId(Integer roleId);

    /**
     * 根据菜单名称获取菜单
     * @param menuName 菜单名称
     * @return
     */
    List<ResourceMenu> getMenuByName(String menuName);

    /**
     * 从中间表删除菜单
     * @param id
     */
    int deleteMenuByMid(int id);

    /**
     * 根据权限id获取权限资源
     * @param permissionId 权限id
     * @return 资源
     */
    List<ResourceMenu> getMenuByPermissionId(Integer permissionId);

}
