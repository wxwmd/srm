package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.synergia.model.ParamDesign;
import com.jaezi.synergia.service.ParamDesignService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 物料参数设计管理请求层
 */
@RestController
@RequestMapping("/syn/design")
public class ParamDesignApi extends BaseApi {

    private ParamDesignService paramDesignService;

    public ParamDesignApi(ParamDesignService paramDesignService) {
        this.paramDesignService = paramDesignService;
    }

    /**
     * 物料参数信息列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(paramDesignService.getAll(filter));
    }

    /**
     * 根据id获取物料参数信息列表
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(paramDesignService.getOneById(id));
    }

    /**
     * 物料参数信息列表添加
     *
     * @param paramDesign 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ParamDesign paramDesign) {
        return returnIntResult(paramDesignService.add(paramDesign));
    }

    /**
     * 物料参数信息列表修改
     *
     * @param paramDesign 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ParamDesign paramDesign) {
        return returnIntResult(paramDesignService.update(paramDesign));
    }

    /**
     * 物料参数信息列表删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(paramDesignService.delete(id));
    }

}
