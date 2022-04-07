package com.jaezi.system.api;

import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.system.model.Permission;
import com.jaezi.system.service.PermissionService;
import com.jaezi.system.service.ResourceMenuService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/5/19 19:15
 * @description 权限API
 */
@RestController
@RequestMapping("/sys/permission")
public class PermissionApi {

    private PermissionService permissionService;

    private ResourceMenuService resourceMenuService;

    public PermissionApi(PermissionService permissionService,ResourceMenuService resourceMenuService) {
        this.permissionService = permissionService;
        this.resourceMenuService = resourceMenuService;
    }

    /**
     * 权限分页
     *
     * @param filter 分页对象
     * @return 对应列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(permissionService.getAll(filter));
    }

    /**
     * 权限根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(permissionService.getOneById(id));
    }

    /**
     * 权限添加
     *
     * @param permission 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody Permission permission) {
        permission.setCreateTime(System.currentTimeMillis());
        return returnIntResult(permissionService.add(permission));
    }

    /**
     * 权限修改
     *
     * @param permission 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody Permission permission) {
        if (permissionService.update(permission) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 权限删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        if (resourceMenuService.getMenuByPermissionId(id).size() != 0) {
            return new JsonResult(ResponseEnum.PERMISSION_HAS_MENU);
        }
        return returnIntResult(permissionService.delete(id));
    }

    private JsonResult returnIntResult(int result) {
        if (result > 0) {
            return JsonResult.SUCCESS;
        } else if (result < 0) {
            return new JsonResult(ResponseEnum.FAILURE.getCode(), "用户已存在");
        } else {
            return JsonResult.FAILURE;
        }
    }

    private JsonResult returnObjectResult(Object obj) {
        return null == obj ? new JsonResult(ResponseEnum.SUCCESS_NO_CONTENT) : new JsonResult(obj);
    }

    /**
     * 根据权限名称获取获取信息
     * @param permissionName 权限名称
     * @return 权限对象
     */
    @GetMapping("/name/{permissionName}")
    public JsonResult getPermissionByName(@PathVariable String permissionName) {
        return returnObjectResult(permissionService.getPermissionByName(permissionName));
    }

    /**
     * 获取权限树
     *
     * @param filter 筛选条件  权限类型
     * @return 权限树
     */
    @GetMapping("/tree")
    public JsonResult getTreePermission(@RequestParam Map<String, String> filter) {
        return returnObjectResult(permissionService.getPermissionTreeByType(filter));
    }

    /**
     * 根据角色获取权限
     *
     * @param roleId 角色ID
     * @return 权限信息
     */
    @GetMapping("/role/{roleId}")
    public JsonResult getPermissionForRole(@PathVariable Integer roleId) {
        return returnObjectResult(permissionService.getPermissionIdsByRoleId(roleId));
    }

    /**
     * 获取权限菜单列表
     *
     * @return 权限菜单
     */
    @GetMapping("/menu")
    public JsonResult getMenuPermission() {
        Map<String, String> filter = new HashMap<>();
//        filter.put("permissionType", "Menu");
        return returnObjectResult(permissionService.getPermissionByType(filter));
    }

    @GetMapping("/api")
    public JsonResult getApiPermission() {
        Map<String, String> filter = new HashMap<>();
        filter.put("permissionType", "Api");
        return returnObjectResult(permissionService.getPermissionByType(filter));
    }

    /**
     * 验证权限名称
     *
     * @param permission 权限名称
     * @return JsonResult
     */
    @PutMapping("/check/name")
    public JsonResult checkName(@RequestBody Permission permission) {
        if (ObjectUtils.isEmpty(permissionService.getPermissionByName(permission.getPermissionName()))) {
            return new JsonResult(ResponseEnum.SUCCESS, false);
        } else {
            return new JsonResult(ResponseEnum.SUCCESS, true);
        }
    }

}
