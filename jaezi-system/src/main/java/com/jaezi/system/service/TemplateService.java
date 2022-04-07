package com.jaezi.system.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.common.util.FileUtil;
import com.jaezi.system.dao.TemplateDao;
import com.jaezi.system.model.Template;
import com.jaezi.system.vo.TemplateVo;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.TEMPLATE_DIR;
import static com.jaezi.common.constant.MinioConst.URL;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

@Service
public class TemplateService extends BaseService<Template, TemplateVo> {
    private TemplateDao templateDao;
    private MinioClient minioClient;

    @Autowired
    public void setBaseDao(TemplateDao templateDao, MinioClient minioClient) {
        super.setBaseDao(templateDao);
        this.templateDao = templateDao;
        this.minioClient = minioClient;
    }

    /**
     * 文件下载
     */
    public void downLoad(HttpServletResponse response, Integer id) {
        Template template = templateDao.getOneById(id);
        if (template != null) {
            String url = template.getUrl();
            String suffix = url.substring(url.lastIndexOf(".") + 1);
            FileUtil.downLoad(template.getUrl(), template.getTemplateName() + "." + suffix, response, minioClient, TEMPLATE_DIR);
        }
    }

    /**
     * 文件上传
     * @param file 文件对象
     * @return
     */
    public String upload(MultipartFile file) {
        String dir = TEMPLATE_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }
}
