package com.jaezi.system.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.MiddleObject;
import com.jaezi.common.constant.RoleConst;
import com.jaezi.system.dao.UserDao;
import com.jaezi.system.model.Role;
import com.jaezi.system.vo.RoleVo;
import com.jaezi.system.dao.RoleDao;
import com.jaezi.system.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/15 15:49
 * @description 角色服务实现类
 */
@Service
public class RoleService extends BaseService<Role, RoleVo> {

    private RoleDao roleDao;

    private UserDao userDao;

    @Autowired
    public void setBaseDao(RoleDao roleDao,UserDao userDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    /**
     * 添加角色信息，添加成功返回角色id
     *
     * @param role 角色信息
     * @return 角色id
     */
    @Override
    public int add(Role role) {
        role.setCreateTime(System.currentTimeMillis());
        if (null != roleDao.getRoleByName(role.getRoleName())) {
            return -1;
        }
        return roleDao.add(role) == 1 ? role.getId() : 0;
    }

    /**
     * 更新角色
     * @param role 角色信息
     * @return 更新条数
     */
    @Override
    public int update(Role role) {
        if (null != roleDao.getRoleByName(role.getRoleName())) {
            return -1;
        }
        return roleDao.update(role);
    }

    /**
     * 根据用户id获取对应的角色名称
     *
     * @param userId 用户id
     * @return 角色名称
     */
    public String getRoleNameByUserId(Integer userId) {
        return roleDao.getRoleNameByUserId(userId);
    }

    /**
     * 根据角色名称获取角色信息
     *
     * @param name 角色名称
     * @return 角色信息
     */
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    /**
     * 为角色添加权限
     *
     * @param roleDto 角色和权限信息
     * @return 添加是否成功
     */
    public int addPermission(RoleDto roleDto) {
        int roleId = add(roleDto);
        if (roleId > 0) {
            List<MiddleObject> middleObjectList = new ArrayList<>();
            for (Integer permissionId : Optional.ofNullable(roleDto.getPermissionIds()).orElse(new ArrayList<>())) {
                middleObjectList.add(new MiddleObject(roleId, permissionId));
            }
            if (!ObjectUtils.isEmpty(middleObjectList)) {
                roleDao.addPermission(middleObjectList);
            }
        }
        return roleId;
    }

    /**
     * 更新角色权限
     * @param roleDto 角色信息
     * @return
     */
    public int updatePermission(RoleDto roleDto) {
        int roleId = roleDao.update(roleDto);
        roleDao.deletePermissionByRoleId(roleDto.getId());
        List<MiddleObject> middleObjectList = new ArrayList<>();
        for (Integer permissionId : Optional.ofNullable(roleDto.getPermissionIds()).orElse(new ArrayList<>())) {
            middleObjectList.add(new MiddleObject(roleDto.getId(), permissionId));
        }
        if (!ObjectUtils.isEmpty(middleObjectList)) {
            roleDao.addPermission(middleObjectList);
        }
        return roleId;
    }

    @Override
    public int delete(Serializable id) {
        int result = super.delete(id);
        if (result > 0) {
            roleDao.deletePermissionByRoleId((Integer) id);
        }
        return result;
    }

    /**
     * 获取不是供应商的角色列表
     *
     */
    public List<RoleVo> getRoleSupplierNot() {
        List<RoleVo> roleVoList = roleDao.getAllVos(null);
        return roleVoList.stream().filter(roleVo -> !RoleConst.OFFICIAL_SUPPLIER_ROLE_NAME.equals(roleVo.getRoleName()) && !RoleConst.GUEST_SUPPLIER_ROLE_NAME.equals(roleVo.getRoleName())).collect(Collectors.toList());
    }
}
