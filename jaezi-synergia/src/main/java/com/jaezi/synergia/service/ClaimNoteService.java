package com.jaezi.synergia.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.common.util.FileUtil;
import com.jaezi.synergia.dao.ClaimNoteDao;
import com.jaezi.synergia.model.ClaimNote;
import com.jaezi.synergia.vo.ClaimNoteVo;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 索赔信息逻辑处理类
 */

@Service
public class ClaimNoteService extends BaseService<ClaimNote, ClaimNoteVo> {

    private ClaimNoteDao claimNoteDao;

    private final MinioClient minioClient;

    public ClaimNoteService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Autowired
    public void setBaseDao(ClaimNoteDao claimNoteDao) {
        super.setBaseDao(claimNoteDao);
        this.claimNoteDao = claimNoteDao;
    }

    /**
     * 根据id获取索赔信息及消息信息
     *
     * @param id ID
     * @return 对象详情
     */
    public ClaimNote getOneById(Integer id, String realName) {
        ClaimNoteVo claimNoteVo = claimNoteDao.getOneById(id);
        if (null != claimNoteVo.getInfoId()){
            claimNoteVo.setInfoList(claimNoteDao.getInfoByInfoId(claimNoteVo.getInfoId(), realName));
        }
        return claimNoteVo;
    }

    /**
     * 根据id删除索赔信息及消息信息
     *
     * @param id ID
     * @return 对象详情
     */
    public int delete(Serializable id) {
        ClaimNoteVo claimNoteVo = claimNoteDao.getOneById(id);

        claimNoteDao.deleteInfoByInfoId(claimNoteVo.getInfoId());

        claimNoteDao.deleteInfoByReplyId(claimNoteVo.getInfoId());

        return claimNoteDao.delete(id);
    }

    public String documentUpload(MultipartFile file) {
        String dir = CLAIM + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    public void down(HttpServletResponse response, Integer id) {
        ClaimNoteVo oneById = claimNoteDao.getOneById(id);
        if (oneById != null) {
            String url = oneById.getDocumentUrl();
            String suffix = url.substring(url.lastIndexOf(".") + 1);
            FileUtil.downLoad(oneById.getDocumentUrl(), oneById.getDocumentName() + "." + suffix, response, minioClient, CLAIM);
        }
    }
}
