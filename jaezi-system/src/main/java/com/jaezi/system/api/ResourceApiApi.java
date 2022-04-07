package com.jaezi.system.api;

import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.system.model.ResourceApi;
import com.jaezi.system.service.ResourceApiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/07/15 15:37
 * @description
 */
@RestController
@RequestMapping("/sys/resource/api")
public class ResourceApiApi {

    private ResourceApiService resourceApiService;

    public ResourceApiApi(ResourceApiService resourceApiService){
        this.resourceApiService = resourceApiService;
    }

    /**
     *  分页
     * @param filter 分页对象
     * @return 对应列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter){
        return new JsonResult(resourceApiService.getAll(filter));
    }

    /**
     * 根据id获取
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id){
        return returnObjectResult(resourceApiService.getOneById(id));
    }

    /**
     *  添加
     * @param api 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ResourceApi api){
        api.setCreateTime(System.currentTimeMillis());
        return returnIntResult(resourceApiService.add(api));
    }

    /**
     *  修改
     * @param api 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ResourceApi api){
        if (resourceApiService.update(api) != 0){
            return JsonResult.SUCCESS;
        }else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     *  删除
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id){
        return returnIntResult(resourceApiService.delete(id));
    }

    /**
     * 获取所有菜单列表（树形结构）
     * @return 菜单列表（树形结构）
     */
    @GetMapping("/tree")
    public JsonResult getTreeApis(){
        return returnObjectResult(resourceApiService.getTreeApis());
    }

    private JsonResult returnIntResult(int result) {
        if(result > 0) {
            return JsonResult.SUCCESS;
        }else if(result < 0){
            return new JsonResult(ResponseEnum.FAILURE.getCode(), "用户已存在");
        }else{
            return JsonResult.FAILURE;
        }
    }

    private JsonResult returnObjectResult(Object obj){
        return null == obj ? new JsonResult(ResponseEnum.SUCCESS_NO_CONTENT) : new JsonResult(obj);
    }
}
