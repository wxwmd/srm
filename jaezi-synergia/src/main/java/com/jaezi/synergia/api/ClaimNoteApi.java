package com.jaezi.synergia.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.synergia.model.ClaimNote;
import com.jaezi.synergia.service.ClaimNoteService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 索赔信息API
 */
@RestController
@RequestMapping("/syn/claim")
public class ClaimNoteApi extends BaseApi {

    private final ClaimNoteService claimNoteService;

    public ClaimNoteApi(ClaimNoteService claimNoteService) {
        this.claimNoteService = claimNoteService;
    }

    /**
     * 索赔信息列表(分页)
     *
     * @param filter 分页对象
     * @return 公告列表
     */
    @GetMapping
    public JsonResult getAll(@RequestParam Map<String, String> filter) {
        filter.put("userType", Objects.requireNonNull(JwtUtil.getUserType()).toString());
        filter.put("realName", Objects.requireNonNull(JwtUtil.getRealName()));

        return returnObjectResult(claimNoteService.getAll(filter));
    }

    /**
     * 索赔信息根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/{id}")
    public JsonResult getOneById(@PathVariable("id") Integer id) {
        String realName = JwtUtil.getRealName();
        return returnObjectResult(claimNoteService.getOneById(id, realName));
    }

    /**
     * 索赔信息添加
     *
     * @param claimNote 对象
     * @return 操作是否成功
     */
    @PostMapping
    public JsonResult add(@RequestBody ClaimNote claimNote) {
        HttpServletRequest request = JwtUtil.getRequest();
        String token = request.getHeader("Credential");

        claimNote.setCreateUser(JwtUtil.getUsername(token));
        claimNote.setCreateTime(System.currentTimeMillis());
        return returnIntResult(claimNoteService.add(claimNote));
    }

    /**
     * 索赔信息修改
     *
     * @param claimNote 对象
     * @return 操作是否成功
     */
    @PutMapping
    public JsonResult update(@RequestBody ClaimNote claimNote) {
        if (claimNoteService.update(claimNote) != 0){
            return JsonResult.SUCCESS;
        }else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 索赔信息删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Integer id) {
        return returnIntResult(claimNoteService.delete(id));
    }

    /**
     * 索赔信息二级界面文件上传
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
        String url = claimNoteService.documentUpload(file);
        if (!ObjectUtils.isEmpty(url)) {
            return new JsonResult(url);
        }
        return JsonResult.FAILURE;
    }

    /**
     * 索赔信息文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    @GetMapping("/download")
    public void down(HttpServletResponse response, @RequestParam Integer id) {
        claimNoteService.down(response, id);
    }

}
