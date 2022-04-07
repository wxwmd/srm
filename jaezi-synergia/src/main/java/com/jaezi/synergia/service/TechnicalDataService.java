package com.jaezi.synergia.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.FileUtil;
import com.jaezi.synergia.dao.MidTechnicalDataPersonDao;
import com.jaezi.synergia.dao.TechnicalDataDao;
import com.jaezi.synergia.dto.TechnicalDataDto;
import com.jaezi.synergia.model.*;
import com.jaezi.synergia.vo.TechnicalDataVo;
import io.minio.MinioClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 技术图档资料逻辑处理层
 */

@Service
public class TechnicalDataService extends BaseService<TechnicalData, TechnicalDataVo> {

    private TechnicalDataDao technicalDataDao;

    private MidTechnicalDataPersonDao midTechnicalDataPersonDao;

    private MinioClient minioClient;

    @Autowired
    public void setBaseDao(TechnicalDataDao technicalDataDao, MidTechnicalDataPersonDao midTechnicalDataPersonDao) {
        super.setBaseDao(technicalDataDao);
        this.technicalDataDao = technicalDataDao;
        this.midTechnicalDataPersonDao = midTechnicalDataPersonDao;
    }

    public TechnicalDataService(MinioClient minioClient) {
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
    public DataGrid<TechnicalData> findAll(Map<String, String> filter) {
        DataGrid<TechnicalData> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<TechnicalData> all = getListByVisible(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<FrequentlyUsedData> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<TechnicalData> list = getListByVisible(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
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
    public List<TechnicalData> getListByVisible(Map<String, String> filter) {
        Integer userType = Integer.valueOf(filter.get("userType"));
        if (userType == 1) {
            return technicalDataDao.findByVisible(filter);
        }
        return technicalDataDao.findAll(filter);
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id
     */
    public void down(HttpServletResponse response, Integer id, String fileName) {
        TechnicalData technicalData = technicalDataDao.getOneById(id);
        if (technicalData != null) {
            if (fileName.equals(technicalData.getName())) {
                String url = technicalData.getUrl();
                String suffix = url.substring(url.lastIndexOf(".") + 1);
                FileUtil.downLoad(technicalData.getUrl(), technicalData.getName() + "." + suffix, response, minioClient, TECHNICAL_DATA_DIR);
            } else if (fileName.equals(technicalData.getDocumentName())) {
                String url = technicalData.getDocumentUrl();
                String suffix = url.substring(url.lastIndexOf(".") + 1);
                FileUtil.downLoad(technicalData.getDocumentUrl(), technicalData.getDocumentName() + "." + suffix, response, minioClient, TECHNICAL_DATA_DIR);
            }
        }
    }

    /**
     * 文件上传
     *
     * @param file 要上传的文件
     * @return String    上传的文件保存的地址路径
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    public String upload(MultipartFile file) {
        String dir = TECHNICAL_DATA_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }

    /**
     * 多文件上传
     *
     * @param file 要上传的文件
     * @return String    上传的文件保存的地址路径
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    public Map<String,String> batchUpload(MultipartFile file) {
        String dir = TECHNICAL_DATA_DIR + getCurrentDate() + "/";
        return FileUtil.uploadAndFileSiz(file, minioClient, dir);
    }

    /**
     * 技术图档资料二级界面文件上传
     *
     * @param file 要上传的文件
     * @return String    上传的文件保存的地址路径
     * @author yx
     * @date 2021/9/1
     * @since 1.0
     */
    public String documentUpload(MultipartFile file) {
        String dir = TECHNICAL_DATA_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }

    /**
     * 添加技术图档资料信息，并设置可见性
     *
     * @param technicalDataDto 技术图档资料信息和可见人员信息id
     * @return int  添加成功数
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    public int addTechnicalData(TechnicalDataDto technicalDataDto) {
        TechnicalData technicalData = new TechnicalData();
        BeanUtils.copyProperties(technicalDataDto, technicalData);
        List<Integer> visiblePersonList = technicalDataDto.getVisiblePersonList();
        Integer visible = technicalData.getVisible();
        int result = technicalDataDao.add(technicalData);
        if (result > 0) {
            if (visible == 0) {
                for (Integer personId : visiblePersonList) {
                    MidTechnicalDataPerson midTechnicalDataPerson = new MidTechnicalDataPerson();
                    midTechnicalDataPerson.setPersonId(personId);
                    midTechnicalDataPerson.setTechnicalDataId(technicalData.getId());
                    midTechnicalDataPersonDao.add(midTechnicalDataPerson);
                }
            }
        }
        return result;
    }


    /**
     * 修改技术图档信息
     *
     * @param technicalDataDto 技术图档资料信息和可见人员id集合
     * @return int
     * 当可见性不为空且为部分供应商可见时才去中间表添加信息，反之直接修改即可
     * @author whj
     * @date 2021/9/7
     * @since 1.0
     */
    public int updateTechnicalData(TechnicalDataDto technicalDataDto) {
        TechnicalData technicalData = new TechnicalData();
        BeanUtils.copyProperties(technicalDataDto, technicalData);
        Integer visible = technicalDataDto.getVisible();
        if (visible != null) {
            midTechnicalDataPersonDao.deleteByTechnicalDataId(technicalData.getId());
            if (visible == 0) {
                List<Integer> list = technicalDataDto.getVisiblePersonList();
                if (list.size() > 0) {
                    for (Integer personId : list) {
                        MidTechnicalDataPerson midTechnicalDataPerson = new MidTechnicalDataPerson();
                        midTechnicalDataPerson.setPersonId(personId);
                        midTechnicalDataPerson.setTechnicalDataId(technicalData.getId());
                        midTechnicalDataPersonDao.add(midTechnicalDataPerson);
                    }
                }
            }
        }
        return technicalDataDao.update(technicalData);
    }

    /**
     * 删除技术图档信息，删除技术图档信息之前要先查看是否有关联此资料的人员，有先删除人员与资料的关系再删除资料信息
     *
     * @param id 资料id
     * @return int
     * @author whj
     * @date 2021/9/7
     * @since 1.0
     */
    @Override
    public int delete(Serializable id) {
        List<MidTechnicalDataPerson> list = midTechnicalDataPersonDao.getListByTechnicalDataId((Integer) id);
        if (list.size() > 0) {
            midTechnicalDataPersonDao.deleteByTechnicalDataId((Integer) id);
        }
        return technicalDataDao.delete(id);
    }

    /**
    * 根据物料号，凭证号，凭证版本号查询技术图档
    * @since 1.0
    * @author whj
    * @date 2021/10/27
    * @param materialNumber 物料号
    * @param certificateNumber 凭证号
    * @param certificateVersions 凭证版本号
    * @return TechnicalData
    */
    public TechnicalData getByMaNumberAndCerNumberAndCerVersions(String materialNumber,String certificateNumber,String certificateVersions){
        return technicalDataDao.findByMaNumberAndCerNumberAndCerVersions(materialNumber,certificateNumber,certificateVersions);
    }

    /**
     * 批量设置技术图档文件路径和可见性
     * @since 1.0
     * @author whj
     * @date 2021/10/27
     * @param fileNames  文件集合
     * @param visibleType   可见性类型
     * @param visiblePersonList   可见人员集合
     * @return String>
     */
    public List<String> batchUpdateTechnicalData(List<Map<String,String>> fileNames, Integer visibleType, List<Integer> visiblePersonList) {
           List<String> result = new ArrayList<>();
        for (Map<String, String> fileName : fileNames) {
            String name = fileName.get(FILE_NAME);
            String url = fileName.get(URL);
            String subName = name.substring(0, name.lastIndexOf("."));
            //文件名格式  物料号-凭证号-凭证版本号
            if (subName.contains("-")){
                String[] split = subName.split("-");
                //物料号
                String materialNumber = split[0];
                //凭证号
                String certificateNumber = split[1];
                //凭证版本号
                String certificateVersions = split[2];
                TechnicalData technicalData = technicalDataDao.findByMaNumberAndCerNumberAndCerVersions(materialNumber, certificateNumber, certificateVersions);
                if (technicalData!=null){
                    technicalData.setUrl(url);
                    technicalData.setVisible(visibleType);
                    technicalData.setName(subName);
                    int updateResult = technicalDataDao.update(technicalData);
                    if (updateResult > 0) {
                        if (visibleType == 0) {
                            for (Integer personId : visiblePersonList) {
                                MidTechnicalDataPerson midTechnicalDataPerson = new MidTechnicalDataPerson();
                                midTechnicalDataPerson.setPersonId(personId);
                                midTechnicalDataPerson.setTechnicalDataId(technicalData.getId());
                                midTechnicalDataPersonDao.add(midTechnicalDataPerson);
                            }
                        }
                    }
                }else{
                    result.add(name);
                }
            }else{
                result.add(name);
            }
        }
        return result;
    }

}
