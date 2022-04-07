package com.jaezi.system.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.system.model.ResourceConfig;
import com.jaezi.system.service.ResourceConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation 系统资源配置请求层
 * @date 2021/07/15 15:37
 * @description 系统资源配置API
 */
@RestController
@RequestMapping("/sys/resource")
public class ResourceConfigApi extends BaseApi {

    private final ResourceConfigService resourceConfigService;

    public ResourceConfigApi(ResourceConfigService resourceConfigService) {
        this.resourceConfigService = resourceConfigService;
    }

    /**
     * 系统资源配置列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(resourceConfigService.getAll(filter));
    }

    /**
     * 系统资源配置修改
     *
     * @param resourceConfig 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ResourceConfig resourceConfig) {
        if ( resourceConfigService.update(resourceConfig) != 0){
            return JsonResult.SUCCESS;
        }else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }
}
