package com.jaezi.bus.finance.api;

import com.jaezi.bus.finance.model.BookBalance;
import com.jaezi.bus.finance.service.BookBalanceService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/6 14:52
 * @description 账面余额API
 */
@RestController
@RequestMapping("/bus/book/balance")
public class BookBalanceApi extends BaseApi {
    private final BookBalanceService bookBalanceService;

    public BookBalanceApi(BookBalanceService bookBalanceService) {
        this.bookBalanceService = bookBalanceService;
    }

    /**
     * 账面余额列表(分页)
     *
     * @param filter 分页对象
     * @return 账面余额列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        if (userType!=null && userType==1){
            filter.put("realName", JwtUtil.getRealName(request));
        }
        return returnObjectResult(bookBalanceService.getAll(filter));
    }

    /**
     * 账面余额根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(bookBalanceService.getOneById(id));
    }

    /**
     * 账面余额添加
     *
     * @param bookBalance 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody BookBalance bookBalance) {
        return returnIntResult(bookBalanceService.add(bookBalance));
    }

    /**
     * 账面余额修改
     *
     * @param bookBalance 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody BookBalance bookBalance) {
        return returnIntResult(bookBalanceService.update(bookBalance));
    }

    /**
     * 账面余额删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(bookBalanceService.delete(id));
    }

    /**
     * 账面余额导出
     *
     * @param response
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, @RequestParam Map<String, String> filter) throws Exception {
        bookBalanceService.export(response, filter);
    }


}
