package com.jaezi.srminterface.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.srminterface.model.TableConfig;
import com.jaezi.srminterface.service.FiledConfigService;
import com.jaezi.srminterface.service.TableConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 数据表配置API
 */
@RestController
@RequestMapping("/sys/table/config")
public class TableConfigApi extends BaseApi {
    private final TableConfigService tableConfigService;
    private final FiledConfigService filedConfigService;

    public TableConfigApi(TableConfigService tableConfigService, FiledConfigService filedConfigService) {
        this.tableConfigService = tableConfigService;
        this.filedConfigService = filedConfigService;
    }

    /**
     * 数据表配置列表（分页）
     *
     * @param filter 分页对象
     * @return 数据表配置列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(tableConfigService.getAll(filter));
    }

    /**
     * 数据表配置根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(tableConfigService.getOneById(id));
    }

    /**
     * 数据表配置添加
     *
     * @param tableConfig 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody TableConfig tableConfig) {
        return returnIntResult(tableConfigService.add(tableConfig));
    }

    /**
     * 数据表配置修改
     *
     * @param tableConfig 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody TableConfig tableConfig) {
        if (tableConfigService.update(tableConfig) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 数据表配置删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(tableConfigService.delete(id));
    }
}
