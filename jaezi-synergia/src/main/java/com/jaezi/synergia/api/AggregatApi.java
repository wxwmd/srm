package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.synergia.model.Aggregat;
import com.jaezi.synergia.service.AggregatService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 总成件信息API
 */
@RestController
@RequestMapping("/syn/aggregat")
public class AggregatApi extends BaseApi {

    private final AggregatService aggregatService;

    public AggregatApi(AggregatService aggregatService) {
        this.aggregatService = aggregatService;
    }

    /**
     * 总成件列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        return returnObjectResult(aggregatService.getAll(filter));
    }

    /**
     * 总成件根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(aggregatService.getOneById(id));
    }

    /**
     * 总成件添加
     *
     * @param aggregat 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody Aggregat aggregat) {
        return returnIntResult(aggregatService.add(aggregat));
    }

    /**
     * 总成件修改
     *
     * @param aggregat 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody Aggregat aggregat) {
        return returnIntResult(aggregatService.update(aggregat));
    }

    /**
     * 总成件删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(aggregatService.delete(id));
    }

    /**
     * 总成件组件根据条件导出excel到浏览器
     *
     * @param filter
     * @return 操作是否成功
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, HttpServletRequest request,
                       @RequestParam Map<String, String> filter) throws Exception {
        Integer userType = JwtUtil.getUserType(request);
        filter.put("userType", String.valueOf(userType));
        filter.put("realName", JwtUtil.getRealName(request));
        aggregatService.export(response, filter);
    }

    /**
     * 总成件组件二级界面文件上传
     *
     * @param file 上传的文件
     * @return JsonResult 文件是否上传成功
     * @author yx
     * @date 2021年8月3日14:25:04
     * @since 1.0
     */
    @PostMapping("/document/upload")
    public JsonResult documentUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return JsonResult.FAILURE;
        }
        String url = aggregatService.documentUpload(file);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }

    /**
     * 总成件组件文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    @GetMapping("/download")
    public void down(HttpServletResponse response, @RequestParam Integer id) {
        aggregatService.down(response, id);
    }

}
