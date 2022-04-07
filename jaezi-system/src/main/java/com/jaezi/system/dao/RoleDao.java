package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.common.bean.MiddleObject;
import com.jaezi.system.model.Role;
import com.jaezi.system.vo.RoleVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/16 17:12
 * @description 角色数据访问对象
 */
@Repository
public interface RoleDao extends BaseDao<Role, RoleVo> {

    /**
     * 根据用户ID获取其角色名
     * @param userId 用户ID
     * @return 角色名
     */
    String getRoleNameByUserId(Integer userId);

    /**
     * 根据角色名称获取角色信息
     * @param name 角色名称
     * @return 角色信息
     */
    Role getRoleByName(String name);

    /**
     * 添加角色权限中间表
     * @param middleObjectList  角色权限中间表
     * @return
     */
    int addPermission(List<MiddleObject> middleObjectList);

    /**
     * 根据角色id删除权限
     * @param id 角色id
     */
    void deletePermissionByRoleId(Integer id);
}
