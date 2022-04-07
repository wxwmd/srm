package com.jaezi.bus.finance.api;

import com.jaezi.bus.finance.model.DetailAccount;
import com.jaezi.bus.finance.service.DetailAccountService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/6 15:02
 * @description 明细账API
 */
@RestController
@RequestMapping("/bus/detail/account")
public class DetailAccountApi extends BaseApi {

    private final DetailAccountService detailAccountService;

    public DetailAccountApi(DetailAccountService detailAccountService) {
        this.detailAccountService = detailAccountService;
    }

    /**
     * 明细账列表(分页)
     *
     * @param filter 分页对象
     * @return 明细账列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        return returnObjectResult(detailAccountService.getAll(filter));
    }

    /**
     * 明细账根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(detailAccountService.getOneById(id));
    }

    /**
     * 明细账添加
     *
     * @param detailAccount 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody DetailAccount detailAccount) {
        return returnIntResult(detailAccountService.add(detailAccount));
    }

    /**
     * 明细账修改
     *
     * @param detailAccount 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody DetailAccount detailAccount) {
        return returnIntResult(detailAccountService.update(detailAccount));
    }

    /**
     * 明细账删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(detailAccountService.delete(id));
    }

    /**
     * 明细账导出
     *
     * @param response
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, @RequestParam Map<String, String> filter) throws Exception {
        detailAccountService.export(response,filter);
    }
}
