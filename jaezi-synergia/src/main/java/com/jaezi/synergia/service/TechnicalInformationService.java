package com.jaezi.synergia.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.FileUtil;
import com.jaezi.synergia.dao.MidTechnicalInformationSupplierDao;
import com.jaezi.synergia.dao.TechnicalInformationDao;
import com.jaezi.synergia.dto.TechnicalInformationDto;
import com.jaezi.synergia.model.*;
import com.jaezi.synergia.vo.TechnicalInformationVo;
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
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3  9:57:24
 * @description
 */
@Service
public class TechnicalInformationService extends BaseService<TechnicalInformation, TechnicalInformationVo> {

    private TechnicalInformationDao technicalInformationDao;

    private final MinioClient minioClient;

    private MidTechnicalInformationSupplierDao midTechnicalInformationSupplierDao;

    @Autowired
    public void setBaseDao(TechnicalInformationDao technicalInformationDao, MidTechnicalInformationSupplierDao midTechnicalInformationSupplierDao) {
        super.setBaseDao(technicalInformationDao);
        this.technicalInformationDao = technicalInformationDao;
        this.midTechnicalInformationSupplierDao = midTechnicalInformationSupplierDao;
    }

    public TechnicalInformationService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    public void down(HttpServletResponse response, Integer id, String fileName) {
        TechnicalInformation technicalInformation = technicalInformationDao.getOneById(id);
        if (technicalInformation != null) {
            if (fileName.equals(technicalInformation.getName())) {
                String url = technicalInformation.getUrl();
                String suffix = url.substring(url.lastIndexOf(".") + 1);
                FileUtil.downLoad(technicalInformation.getUrl(), technicalInformation.getName() + "." + suffix, response, minioClient, TECHNICAL_INFORMATION_DIR);
            } else if (fileName.equals(technicalInformation.getDocumentName())) {
                String url = technicalInformation.getDocumentUrl();
                String suffix = url.substring(url.lastIndexOf(".") + 1);
                FileUtil.downLoad(technicalInformation.getDocumentUrl(), technicalInformation.getDocumentName() + "." + suffix, response, minioClient, TECHNICAL_INFORMATION_DIR);
            }
        }
    }

    /**
     * 文件上传
     *
     * @param file 上传的文件
     * @return String> k->v
     * <url,文件访问路径>
     * <fileSize,文件大小>
     * @author yx
     * @date 2021年8月3日14:23:49
     * @since 1.0
     */
    public String upload(MultipartFile file) {
        String dir = TECHNICAL_INFORMATION_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }

    /**
     * 技术资料交流二级界面文件上传
     *
     * @param file 上传的文件
     * @return String> k->v
     * <url,文件访问路径>
     * <fileSize,文件大小>
     * @author yx
     * @date 2021年8月3日14:23:49
     * @since 1.0
     */
    public String documentUpload(MultipartFile file, Integer id) {
        String dir = TECHNICAL_INFORMATION_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            TechnicalInformation oneById = technicalInformationDao.getOneById(id);
            oneById.setDocumentName(file.getOriginalFilename());
            oneById.setDocumentUrl(result.get(result.get(URL)));
            technicalInformationDao.update(oneById);
            return result.get(URL);

        }
        return null;
    }

    /**
     * 查询技术资料
     *
     * @param filter 查询条件
     * @return TechnicalInformation> 技术资料对象
     * @author yx
     * @date 2021年8月9日14:55:47
     * @since 1.0
     */
    public DataGrid<TechnicalInformation> findAll(Map<String, String> filter) {
        DataGrid<TechnicalInformation> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<TechnicalInformation> all = getListByVisible(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<TechnicalInformation> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<TechnicalInformation> list = getListByVisible(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 获取技术资料
     *
     * @param filter
     * @return
     */
    public List<TechnicalInformation> getListByVisible(Map<String, String> filter) {
        Integer userType = Integer.valueOf(filter.get("userType"));
        if (userType == 1) {
            return technicalInformationDao.findByVisible(filter);
        }
        return technicalInformationDao.findAll(filter);
    }

    /**
     * 设置技术资料可见性
     *
     * @param dataIdList        技术资料id集合
     * @param visiblePersonList 可见人员id集合
     * @param visibleType       可见类型
     * @return Integer 设置条数
     * @author yx
     * @date 2021年8月9日11:32:12
     * @since 1.0
     */
    public Integer setVisible(List<Integer> dataIdList, List<Integer> visiblePersonList, Integer visibleType) {
        int result = 0;
        //先根据 技术资料id集合 修改包含的技术资料的类型为传过来的 visibleType
        for (Integer dataId : dataIdList) {
            TechnicalInformation technicalInformationData = technicalInformationDao.getOneById(dataId);
            technicalInformationData.setVisible(visibleType);
            technicalInformationData.setUpdateTime(String.valueOf(System.currentTimeMillis()));
            int update = technicalInformationDao.update(technicalInformationData);
            if (update > 0) {
                result++;
            }
        }
        //如果 visibleType 为0的话，则代表技术资料的可见性为部分供应商，就将人员id和技术资料id添加到人员生产资料中间表
        if (visibleType == 0) {
            for (Integer personId : visiblePersonList) {
                for (Integer informationId : dataIdList) {
                    MidTechnicalInformationSupplier midTechnicalInformationSupplier = new MidTechnicalInformationSupplier();
                    midTechnicalInformationSupplier.setPersonId(personId);
                    midTechnicalInformationSupplier.setInformationId(informationId);
                    midTechnicalInformationSupplierDao.add(midTechnicalInformationSupplier);
                }

            }
        }
        return result;
    }

    /**
     * 修改技术资料可见状态
     *
     * @param dataIdList        技术资料id集合
     * @param visiblePersonList 可见人员id集合
     * @param visibleType       可见状态
     * @return int 修改条数
     * @author yx
     * @date 2021年8月9日14:44:55
     * @since 1.0
     */
    public int updateVisible(List<Integer> dataIdList, List<Integer> visiblePersonList, Integer visibleType) {
        int result = 0;
        //判断传过来的技术资料id在技术资料表中是否存在，如果存在则直接删除人员技术资料中间表
        List<TechnicalInformation> technicalInformationList = technicalInformationDao.findByIds(dataIdList);
        if (technicalInformationList.size() > 0) {
            midTechnicalInformationSupplierDao.deleteByInformationIds(dataIdList);
        }
        //  如果可见状态为0 就代表技术资料为部分供应商可见，则将技术资料id和可见人员id添加到人员技术资料中间表
        if (visibleType == 0) {
            for (Integer personId : visiblePersonList) {
                for (Integer informationId : dataIdList) {
                    MidTechnicalInformationSupplier midTechnicalInformationSupplier = new MidTechnicalInformationSupplier();
                    midTechnicalInformationSupplier.setPersonId(personId);
                    midTechnicalInformationSupplier.setInformationId(informationId);
                    midTechnicalInformationSupplierDao.add(midTechnicalInformationSupplier);
                }
            }
        }
        // 修改每条技术资料信息的可见状态 可见状态设置为传过来的 visibleType
        for (Integer id : dataIdList) {
            TechnicalInformation technicalInformationData = technicalInformationDao.getOneById(id);
            technicalInformationData.setVisible(visibleType);
            technicalInformationData.setUpdateTime(String.valueOf(System.currentTimeMillis()));
            int update = technicalInformationDao.update(technicalInformationData);
            if (update > 0) {
                result++;
            }
        }
        return result;
    }

    /**
     * 添加技术资料信息和设置可见性
     *
     * @param technicalInformationDto 技术资料信息和可见人员id
     * @return int  添加成功数
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    public int addTechnicalInformation(TechnicalInformationDto technicalInformationDto) {
        TechnicalInformation technicalInformation = new TechnicalInformation();
        BeanUtils.copyProperties(technicalInformationDto, technicalInformation);
        technicalInformation.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        List<Integer> visiblePersonList = technicalInformationDto.getVisiblePersonList();
        Integer visible = technicalInformation.getVisible();
        int result = technicalInformationDao.add(technicalInformation);
        if (result > 0) {
            if (visible == 0) {
                for (Integer personId : visiblePersonList) {
                    MidTechnicalInformationSupplier midTechnicalInformationSupplier = new MidTechnicalInformationSupplier();
                    midTechnicalInformationSupplier.setPersonId(personId);
                    midTechnicalInformationSupplier.setInformationId(technicalInformation.getId());
                    midTechnicalInformationSupplierDao.add(midTechnicalInformationSupplier);
                }
            }
        }
        return result;
    }

    /**
     * 修改技术资料信息
     *
     * @param technicalInformationDto 技术资料信息和可见人员id集合
     * @return int
     * 当可见性不为空且为部分供应商可见时才去中间表添加信息，反之直接修改即可
     * @author whj
     * @date 2021/9/7
     * @since 1.0
     */
    public int updateTechnicalInformation(TechnicalInformationDto technicalInformationDto) {
        TechnicalInformation technicalInformation = new TechnicalInformation();
        BeanUtils.copyProperties(technicalInformationDto, technicalInformation);
        technicalInformation.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        Integer visible = technicalInformationDto.getVisible();
        if (visible != null) {
            midTechnicalInformationSupplierDao.deleteByInformationId(technicalInformation.getId());
            if (visible == 0) {
                List<Integer> list = technicalInformationDto.getVisiblePersonList();
                if (list.size() > 0) {
                    for (Integer personId : list) {
                        MidTechnicalInformationSupplier midTechnicalDataPerson = new MidTechnicalInformationSupplier();
                        midTechnicalDataPerson.setPersonId(personId);
                        midTechnicalDataPerson.setInformationId(technicalInformation.getId());
                        midTechnicalInformationSupplierDao.add(midTechnicalDataPerson);
                    }
                }
            }
        }
        return technicalInformationDao.update(technicalInformation);
    }

    /**
     * 删除技术资料信息，删除技术资料信息之前要先查看是否有关联此资料的人员，有先删除人员与资料的关系再删除资料信息
     *
     * @param id 资料id
     * @return int
     * @author whj
     * @date 2021/9/7
     * @since 1.0
     */
    @Override
    public int delete(Serializable id) {
        List<MidTechnicalInformationSupplier> list = midTechnicalInformationSupplierDao.getListByTechnicalInformationId((Integer) id);
        if (list.size() > 0) {
            midTechnicalInformationSupplierDao.deleteByInformationId((Integer) id);
        }
        return technicalInformationDao.delete(id);
    }
}
