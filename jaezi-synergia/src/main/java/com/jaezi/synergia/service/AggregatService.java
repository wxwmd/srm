package com.jaezi.synergia.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.common.util.ExcelUtil;
import com.jaezi.common.util.FileUtil;
import com.jaezi.synergia.dao.AggregatDao;
import com.jaezi.synergia.model.Aggregat;
import com.jaezi.synergia.model.FrequentlyUsedData;
import com.jaezi.synergia.vo.AggregatVo;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
public class AggregatService extends BaseService<Aggregat, AggregatVo> {

    private AggregatDao aggregatDao;

    private final MinioClient minioClient;

    public AggregatService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Autowired
    public void setBaseDao(AggregatDao aggregatDao) {
        super.setBaseDao(aggregatDao);
        this.aggregatDao = aggregatDao;
    }
    /**
     * 导出excel
     *
     * @param filter 物料号
     * @return void
     */
    public void export(HttpServletResponse response, Map<String, String> filter) throws Exception {
        List<Aggregat> aggregatList = aggregatDao.getAllByMaterial(filter);
        if (ObjectUtils.isEmpty(aggregatList)) {
            return;
        }
        ExcelUtil.export(response, aggregatList, "总成件信息", "模板", Aggregat.class);
    }

    public String documentUpload(MultipartFile file) {
        String dir = AGGREGAT + getCurrentDate() + "/";
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
        Aggregat oneById = aggregatDao.getOneById(id);
        if (oneById != null) {
            String url = oneById.getDocumentUrl();
            String suffix = url.substring(url.lastIndexOf(".") + 1);
            FileUtil.downLoad(oneById.getDocumentUrl(), oneById.getDocumentName() + "." + suffix, response, minioClient, AGGREGAT);
        }
    }
}
