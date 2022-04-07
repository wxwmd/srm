package com.jaezi.system.service;


import com.jaezi.common.base.BaseService;
import com.jaezi.system.model.ResourceMenu;
import com.jaezi.system.vo.ResourceMenuVo;
import com.jaezi.system.dao.ResourceMenuDao;
import com.jaezi.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/15 15:49
 * @description 菜单服务实现类
 */
@Service
public class ResourceMenuService extends BaseService<ResourceMenu, ResourceMenuVo> {

    private RoleDao roleDao;
    private ResourceMenuDao resourceMenuDao;

    @Autowired
    public void setBaseDao(ResourceMenuDao resourceMenuDao, RoleDao roleDao){
        super.setBaseDao(resourceMenuDao);
        this.resourceMenuDao = resourceMenuDao;
        this.roleDao = roleDao;
    }

    /**
     * 添加菜单
     * 同一层级菜单名不能相同
     * @param menu 菜单
     * @return 添加是否成功
     */
    @Override
    public int add(ResourceMenu menu) {
        List<ResourceMenu> menuList = resourceMenuDao.getMenuByName(menu.getMenuName());
        for(ResourceMenu m : menuList) {
            if (m.getPid().equals(menu.getPid())) {
                return -1;
            }
        }
        return resourceMenuDao.add(menu);
    }

    /**
     * 更新菜单
     * 同一层级菜单名不能相同
     * @param menu 菜单
     * @return 更新是否成功
     */
    @Override
    public int update(ResourceMenu menu) {
        return resourceMenuDao.update(menu);
    }

    /**
     * 获取所有菜单列表（树形结构）
     * @return 菜单列表（树形结构）
     */
    public List<ResourceMenuVo> getTreeMenus(){
        List<ResourceMenuVo> result = new ArrayList<>();
        List<ResourceMenuVo> menus = resourceMenuDao.getAllVos(null);
        if(null == menus){
            return null;
        }
        Map<Integer, ResourceMenuVo> map = menus.stream().collect(Collectors.toMap(ResourceMenu::getId, menu -> menu ));
        menus.forEach(menuVo -> {
            if(menuVo.getPid() == 0){
                result.add(menuVo);
                result.sort(Comparator.comparing(ResourceMenu::getMenuOrder));
            } else{
                map.get(menuVo.getPid()).getChildren().add(menuVo);
                List<ResourceMenuVo> vo = map.get(menuVo.getPid()).getChildren();
                vo.sort(Comparator.comparing(ResourceMenu::getMenuOrder));
            }
        });
        return result;
    }

    /**
     * 根据角色id获取对应的菜单列表 (树形结构)
     * @param roleId  角色id
     * @return 菜单列表（树形结构）
     */
    public List<ResourceMenuVo> getMenusByRoleId(Integer roleId){
        List<ResourceMenuVo> menuVos = new ArrayList<>();
        List<ResourceMenuVo> menus = resourceMenuDao.getMenusByRoleId(roleId);
        if(menus.isEmpty()){
            return null;
        }
        Map<Integer, ResourceMenuVo> map = menus.stream().collect(Collectors.toMap(ResourceMenu::getId, menu -> menu));
        for (ResourceMenuVo menu : menus) {
            Integer pid = menu.getPid();
            if (pid == 0) {
                menuVos.add(menu);
                menuVos.sort(Comparator.comparing(ResourceMenu::getMenuOrder));
            } else if (map.containsKey(pid)) {
                List<ResourceMenuVo> voList = map.get(pid).getChildren();
                voList.add(menu);
                voList.sort(Comparator.comparing(ResourceMenu::getMenuOrder));
            }
        }
        return menuVos;
    }

    @Override
    public int delete(Serializable id) {
        int result = super.delete(id);
        if(result > 0){
            resourceMenuDao.deleteMenuByMid((int)id);
        }
        return result;
    }

    /**
     * 根据权限id获取权限资源
     * @param permissionId 权限id
     * @return 资源
     */
    public List<ResourceMenu> getMenuByPermissionId(Integer permissionId) {
        return resourceMenuDao.getMenuByPermissionId(permissionId);
    }
}
