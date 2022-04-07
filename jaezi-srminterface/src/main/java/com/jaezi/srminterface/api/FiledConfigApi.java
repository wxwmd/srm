package com.jaezi.srminterface.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.srminterface.model.FiledConfig;
import com.jaezi.srminterface.service.FiledConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 数据列配置API
 */
@RestController
@RequestMapping("/sys/filed/config")
public class FiledConfigApi extends BaseApi {
    private final FiledConfigService filedConfigService;

    public FiledConfigApi(FiledConfigService filedConfigService) {
        this.filedConfigService = filedConfigService;
    }

    /**
     * 数据列配置列表（分页）
     *
     * @param filter 分页对象
     * @return 数据表配置列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(filedConfigService.getAll(filter));
    }

    /**
     * 数据列配置根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(filedConfigService.getOneById(id));
    }

    /**
     * 数据列配置添加
     *
     * @param filedConfig 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody FiledConfig filedConfig) {
        return returnIntResult(filedConfigService.add(filedConfig));
    }

    /**
     * 数据列配置修改
     *
     * @param filedConfig 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody FiledConfig filedConfig) {
        if (filedConfigService.update(filedConfig) != 0){
            return JsonResult.SUCCESS;
        }else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 数据列配置删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(filedConfigService.delete(id));
    }
}
