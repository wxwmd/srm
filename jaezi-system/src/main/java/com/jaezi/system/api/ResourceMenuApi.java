package com.jaezi.system.api;

import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.system.model.ResourceMenu;
import com.jaezi.system.service.ResourceMenuService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/8/28  16:46
 * @description 菜单API
 */
@RestController
@RequestMapping("/sys/resource/menu")
public class ResourceMenuApi {

    private ResourceMenuService resourceMenuService;

    public ResourceMenuApi(ResourceMenuService resourceMenuService) {
        this.resourceMenuService = resourceMenuService;
    }

    /**
     * 菜单列表（分页）
     *
     * @param filter 分页对象
     * @return 对应列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(resourceMenuService.getAll(filter));
    }

    /**
     * 菜单根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(resourceMenuService.getOneById(id));
    }

    /**
     * 菜单添加
     *
     * @param menu 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ResourceMenu menu) {
        menu.setCreateTime(System.currentTimeMillis());
        return returnIntResult(resourceMenuService.add(menu));
    }

    /**
     * 菜单修改
     *
     * @param menu 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ResourceMenu menu) {
        if (resourceMenuService.update(menu) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 菜单删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(resourceMenuService.delete(id));
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
     * 获取所有菜单列表（树形结构）
     *
     * @return 菜单列表（树形结构）
     */
    @GetMapping("/tree")
    public JsonResult getTreeMenus() {
        return returnObjectResult(resourceMenuService.getTreeMenus());
    }

    /**
     * 根据角色id获取对应的菜单列表
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    @GetMapping("/role/{roleId}")
    public JsonResult getMenusByRoleId(@PathVariable Integer roleId) {
        return returnObjectResult(resourceMenuService.getMenusByRoleId(roleId));
    }

}
