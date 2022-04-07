package com.jaezi.system.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.system.model.Template;
import com.jaezi.system.service.TemplateService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/16 17:12
 * @description 模板API
 */
@RestController
@RequestMapping("/syn/template")
public class TemplateApi extends BaseApi {
    private final TemplateService templateService;

    public TemplateApi(TemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * 模板列表（分页）
     *
     * @param filter  查询条件列表
     * @param request request对象
     * @return JsonResult 技术资料列表
     * @author yx
     * @date 2021年8月9日15:57:51
     * @since 1.0
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter, HttpServletRequest request) {
        return returnObjectResult(templateService.getAll(filter));
    }

    /**
     * 根据id查询模版资料
     *
     * @param id 模版id
     * @return JsonResult 技术资料兑现
     * TODO 方法逻辑
     * @author yx
     * @date 2021年8月9日15:56:42
     * @since 1.0
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable Integer id) {
        return returnObjectResult(templateService.getOneById(id));
    }

    /**
     * 添加模版资料
     *
     * @param template 模板资料对象
     * @return JsonResult 是否添加成功
     * @author yx
     * @date 2021年8月9日15:55:34
     * @since 1.0
     */
    @PostMapping
    public JsonResult add(@RequestBody Template template) {
        template.setCreateTime(System.currentTimeMillis());
        template.setCreateUser(JwtUtil.getUsername(JwtUtil.getRequest().getHeader("Credential")));
        return returnIntResult(templateService.add(template));
    }

    /**
     * 修改模版资料
     *
     * @param template 模板资料对象
     * @return JsonResult
     * @author yx
     * @date 2021年8月9日15:54:45
     * @since 1.0
     */
    @PutMapping
    public JsonResult update(@RequestBody Template template) {
        if (templateService.update(template) != 0) {
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 删除模版资料
     *
     * @return JsonResult 是否删除成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(templateService.delete(id));
    }

    /**
     * 模版资料文件下载
     *
     * @param response response对象
     * @param id       模版资料id
     * @return JsonResult 是否删除成功
     */
    @GetMapping("/download")
    public void down(HttpServletResponse response, @RequestParam Integer id) {
        templateService.downLoad(response, id);
    }

    /**
     * 模板文件上传
     *
     * @param file 上传的文件
     * @return JsonResult 是否删除成功
     */
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
            return JsonResult.FAILURE;
        }
        String url = templateService.upload(file);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }

}
