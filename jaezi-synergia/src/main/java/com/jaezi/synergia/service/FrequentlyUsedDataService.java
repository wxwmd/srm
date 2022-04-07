package com.jaezi.synergia.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.FileUtil;
import com.jaezi.synergia.dao.FrequentlyUsedDataDao;
import com.jaezi.synergia.dao.MidFrequentlyUsedDataPersonDao;
import com.jaezi.synergia.dto.FrequentlyUsedDataDto;
import com.jaezi.synergia.model.FrequentlyUsedData;
import com.jaezi.synergia.model.MidFrequentlyUsedDataPerson;
import com.jaezi.synergia.vo.FrequentlyUsedDataVo;
import io.minio.MinioClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 常用资料逻辑处理层
 */

@Service
public class FrequentlyUsedDataService extends BaseService<FrequentlyUsedData, FrequentlyUsedDataVo> {

    private FrequentlyUsedDataDao frequentlyUsedDataDao;

    private MidFrequentlyUsedDataPersonDao midFrequentlyUsedDataPersonDao;

    private final MinioClient minioClient;

    @Autowired
    public void setBaseDao(FrequentlyUsedDataDao frequentlyUsedDataDao, MidFrequentlyUsedDataPersonDao midFrequentlyUsedDataPersonDao) {
        super.setBaseDao(frequentlyUsedDataDao);
        this.frequentlyUsedDataDao = frequentlyUsedDataDao;
        this.midFrequentlyUsedDataPersonDao = midFrequentlyUsedDataPersonDao;
    }

    public FrequentlyUsedDataService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    /**
     * 查询常用资料列表
     *
     * @param filter 过滤条件
     * @return FrequentlyUsedData>
     * @author wanghaojie
     * @date 2021/8/4
     * @since 1.0
     */
    public DataGrid<FrequentlyUsedData> findAll(Map<String, String> filter) {
        DataGrid<FrequentlyUsedData> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<FrequentlyUsedData> all = getListByVisible(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<FrequentlyUsedData> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<FrequentlyUsedData> list = getListByVisible(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }


    public List<FrequentlyUsedData> getListByVisible(Map<String, String> filter) {
        Integer userType = Integer.valueOf(filter.get("userType"));
        if (userType == 1) {
            return frequentlyUsedDataDao.findByVisible(filter);
        }
        return frequentlyUsedDataDao.findAll(filter);
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    public void down(HttpServletResponse response, Integer id, String fileName) {
        FrequentlyUsedData frequentlyUsedData = frequentlyUsedDataDao.getOneById(id);
        if (frequentlyUsedData != null) {
            if (fileName.equals(frequentlyUsedData.getName())) {
                String url = frequentlyUsedData.getUrl();
                String suffix = url.substring(url.lastIndexOf(".") + 1);
                FileUtil.downLoad(frequentlyUsedData.getUrl(), frequentlyUsedData.getName() + "." + suffix, response, minioClient, FREQUENTLY_USED_DATA_DIR);
            } else if (fileName.equals(frequentlyUsedData.getDocumentName())) {
                String url = frequentlyUsedData.getDocumentUrl();
                String suffix = url.substring(url.lastIndexOf(".") + 1);
                FileUtil.downLoad(frequentlyUsedData.getDocumentUrl(), frequentlyUsedData.getDocumentName() + "." + suffix, response, minioClient, FREQUENTLY_USED_DATA_DIR);
            }
        }
    }


    /**
     * 删除常用资料信息，删除常用资料信息之前要先查看是否有关联此资料的人员，有先删除人员与资料的关系再删除资料信息
     *
     * @param id 资料id
     * @return int
     * @author whj
     * @date 2021/9/7
     * @since 1.0
     */
    @Override
    public int delete(Serializable id) {
        List<MidFrequentlyUsedDataPerson> list = midFrequentlyUsedDataPersonDao.getListByFrequentlyUsedDataId((Integer) id);
        if (list.size() > 0) {
            midFrequentlyUsedDataPersonDao.deleteByFrequentlyUsedDataId((Integer) id);
        }
        return frequentlyUsedDataDao.delete(id);
    }

    /**
     * 文件上传
     *
     * @param file 上传的文件
     * @return String  上传文件保存的路径
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    public String upload(MultipartFile file) {
        String dir = FREQUENTLY_USED_DATA_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }

    /**
     * 常用资料二级页面文件上传
     *
     * @param file 上传的文件
     * @return String  上传文件保存的路径
     * @author yx
     * @date 2021/9/1
     * @since 1.0
     */
    public String documentUpload(MultipartFile file,Integer id) {
        String dir = FREQUENTLY_USED_DATA_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            FrequentlyUsedData oneById = frequentlyUsedDataDao.getOneById(id);
            oneById.setDocumentName(file.getOriginalFilename());
            oneById.setDocumentUrl(result.get(URL));
            frequentlyUsedDataDao.update(oneById);
            return result.get(URL);
        }
        return null;
    }


    /**
     * 添加常用资料并设置可见性
     *
     * @param frequentlyUsedDataDto 常用资料数据和可见人员数据
     * @return int  添加成功数
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    public int addFrequentlyUsedData(FrequentlyUsedDataDto frequentlyUsedDataDto) {
        FrequentlyUsedData frequentlyUsedData = new FrequentlyUsedData();
        BeanUtils.copyProperties(frequentlyUsedDataDto, frequentlyUsedData);
        frequentlyUsedData.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        List<Integer> visiblePersonList = frequentlyUsedDataDto.getVisiblePersonList();
        Integer visible = frequentlyUsedData.getVisible();
        int result = frequentlyUsedDataDao.add(frequentlyUsedData);
        if (result > 0) {
            if (visible == 0) {
                for (Integer personId : visiblePersonList) {
                    MidFrequentlyUsedDataPerson midFrequentlyUsedDataPerson = new MidFrequentlyUsedDataPerson();
                    midFrequentlyUsedDataPerson.setPersonId(personId);
                    midFrequentlyUsedDataPerson.setFrequentlyUsedDataId(frequentlyUsedData.getId());
                    midFrequentlyUsedDataPersonDao.add(midFrequentlyUsedDataPerson);
                }
            }
        }
        return result;
    }

    /**
     * 修改常用资料信息
     *
     * @param frequentlyUsedDataDto 常用资料信息和可见人员id集合
     * @return int
     * 当可见性不为空且为部分供应商可见时才去中间表添加信息，反之直接修改即可
     * @author whj
     * @date 2021/9/7
     * @since 1.0
     */
    public int updateUsedData(FrequentlyUsedDataDto frequentlyUsedDataDto) {
        FrequentlyUsedData frequentlyUsedData = new FrequentlyUsedData();
        BeanUtils.copyProperties(frequentlyUsedDataDto, frequentlyUsedData);
        frequentlyUsedData.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        Integer visible = frequentlyUsedDataDto.getVisible();
        if (visible != null) {
            midFrequentlyUsedDataPersonDao.deleteByFrequentlyUsedDataId(frequentlyUsedData.getId());
            if (visible == 0) {
                List<Integer> visiblePersonList = frequentlyUsedDataDto.getVisiblePersonList();
                if (visiblePersonList.size() > 0) {
                    for (Integer personId : visiblePersonList) {
                        MidFrequentlyUsedDataPerson midFrequentlyUsedDataPerson = new MidFrequentlyUsedDataPerson();
                        midFrequentlyUsedDataPerson.setPersonId(personId);
                        midFrequentlyUsedDataPerson.setFrequentlyUsedDataId(frequentlyUsedData.getId());
                        midFrequentlyUsedDataPersonDao.add(midFrequentlyUsedDataPerson);
                    }
                }
            }
        }
        return frequentlyUsedDataDao.update(frequentlyUsedData);
    }
}
