package com.jaezi.srminterface.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.srminterface.model.SqlConfig;
import com.jaezi.srminterface.service.SqlConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 自定义sql配置API
 */
@RestController
@RequestMapping("/sys/sql/config")
public class SqlConfigApi extends BaseApi {

    private final SqlConfigService sqlConfigService;

    public SqlConfigApi(SqlConfigService sqlConfigService) {
        this.sqlConfigService = sqlConfigService;
    }

    /**
     * 自定义sql配置列表（分页）
     *
     * @param filter 分页对象
     * @return 字典类型列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(sqlConfigService.getAll(filter));
    }

    /**
     * 自定义sql配置根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(sqlConfigService.getOneById(id));
    }

    /**
     * 自定义sql配置添加
     *
     * @param sqlConfig 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody SqlConfig sqlConfig) {
        return returnIntResult(sqlConfigService.add(sqlConfig));
    }

    /**
     * 自定义sql配置修改
     *
     * @param sqlConfig 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody SqlConfig sqlConfig) {
        if (sqlConfigService.update(sqlConfig) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 自定义sql配置删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(sqlConfigService.delete(id));
    }

    /**
     * 手动执行sql
     *
     * @param sqlConfig 对象
     * @return 操作是否成功
     */
    @PostMapping("/manual/run")
    public JsonResult manualRun(@RequestBody SqlConfig sqlConfig) {
        int run = sqlConfigService.manualRun(sqlConfig);
        if (run == -1) {
            return new JsonResult(JsonResult.FAILURE.getCode(), "SQL运行异常");
        }
        return JsonResult.SUCCESS;
    }
}
