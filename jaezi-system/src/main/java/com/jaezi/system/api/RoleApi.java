package com.jaezi.system.api;

import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.system.dto.RoleDto;
import com.jaezi.system.model.Role;
import com.jaezi.system.service.RoleService;
import com.jaezi.system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/16 17:12
 * @description 角色API
 */
@RestController
@RequestMapping("/sys/role")
public class RoleApi {

    private RoleService roleService;

    private UserService userService;

    public RoleApi(RoleService roleService,UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    /**
     * 角色列表（分页）
     *
     * @param filter 分页对象
     * @return 对应列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(roleService.getAll(filter));
    }

    /**
     * 角色根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(roleService.getOneById(id));
    }

    /**
     * 获取不是供应商的角色
     */
    @GetMapping("/supplier/not")
    public JsonResult getRoleSupplierNot(){
        return new JsonResult(roleService.getRoleSupplierNot());
    }

    /**
     * 角色添加
     *
     * @param role 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody Role role) {
        return returnIntResult(roleService.add(role));
    }

    /**
     * 角色修改
     *
     * @param role 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody Role role) {
        if (roleService.update(role) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 角色删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        if (userService.getUserByRoleId(id).size() != 0){
            return new JsonResult(ResponseEnum.ROLE_HAS_USER);
        }
        return returnIntResult(roleService.delete(id));
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
     * 根据名称获取角色信息
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    @GetMapping("/name/{roleName}")
    public JsonResult checkRoleExist(@PathVariable String roleName) {
        return returnObjectResult(roleService.getRoleByName(roleName));
    }

    /**
     * 角色添加权限
     *
     * @param roleDto 角色信息
     * @return 角色和权限的中间表 插入条数
     */
    @PostMapping("/permission")
    public JsonResult addPermission(@RequestBody RoleDto roleDto) {
        return returnIntResult(roleService.addPermission(roleDto));
    }

    /**
     * 修改角色权限
     *
     * @param roleDto 角色信息
     * @return 修改是否成功
     */
    @PutMapping("/permission")
    public JsonResult updatePermission(@RequestBody RoleDto roleDto) {
        if (roleService.updatePermission(roleDto) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

}
