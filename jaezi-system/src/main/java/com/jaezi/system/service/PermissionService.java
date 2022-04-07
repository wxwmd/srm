package com.jaezi.system.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.system.dao.PermissionDao;
import com.jaezi.system.model.Permission;
import com.jaezi.system.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/15 15:49
 * @description 角色服务实现类
 */
@Service
public class PermissionService extends BaseService<Permission, PermissionVo> {

    private PermissionDao permissionDao;

    @Autowired
    public void setBaseDao(PermissionDao permissionDao) {
        super.setBaseDao(permissionDao);
        this.permissionDao = permissionDao;
    }

    /**
     * 根据权限名称获取获取信息
     * @param permissionName 权限名称
     * @return 权限对象
     */
    public Permission getPermissionByName(String permissionName) {
        return permissionDao.getPermissionByName(permissionName);
    }

    /**
     * 根据权限类型获取权限树
     * 查询条件：
     * 1.权限类型 permissionType
     *
     * @return 权限树
     */
    public List<PermissionVo> getPermissionTreeByType(Map<String, String> filter) {
        List<PermissionVo> permissions = permissionDao.getAllVos(filter);
        if (null == permissions) {
            return null;
        }
        Map<Integer, List<PermissionVo>> map = permissions.stream().collect(Collectors.groupingBy(Permission::getPid));
        List<PermissionVo> result = map.remove(0);
        extractPermission(map, result);
        return result;
    }

    /**
     * 根据角色id 获取权限id集合
     * @param roleId 角色id
     * @return 权限id集合
     */
    public List<Integer> getPermissionIdsByRoleId(Integer roleId) {
        return permissionDao.getPermissionIdsByRoleId(roleId);
    }

    /**
     * 根据权限类型获取权限树
     * 查询条件：
     * 1.权限类型 permissionType  Api  Menu
     *
     * @return 权限树
     */
    public List<PermissionVo> getPermissionByType(Map<String, String> filter) {
        List<PermissionVo> permissions = permissionDao.getAllVos(filter);
        if (null == permissions) {
            return null;
        }
        if (0 == permissions.size()) {
            return null;
        }
        Map<Integer, List<PermissionVo>> map = permissions.stream().collect(Collectors.groupingBy(Permission::getPid));
        List<PermissionVo> result = new ArrayList<>(map.remove(0));
        extractPermission(map, result);
        return result.stream().sorted(Comparator.comparing(Permission::getId)).collect(Collectors.toList());
    }

    private void extractPermission(Map<Integer, List<PermissionVo>> map, List<PermissionVo> result) {
        for (PermissionVo pv : result) {
            List<PermissionVo> remove = map.remove(pv.getId());
            Map map1 = new HashMap();
            if (remove != null && !remove.isEmpty()) {
                pv.setChildren(remove);
            }
            extractPermission(map, pv.getChildren());
        }
    }

}
