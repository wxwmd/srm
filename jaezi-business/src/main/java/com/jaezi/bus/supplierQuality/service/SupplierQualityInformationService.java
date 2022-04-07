package com.jaezi.bus.supplierQuality.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.supplierQuality.dao.MidSupplierQualityInformationPersonDao;
import com.jaezi.bus.supplierQuality.dao.SupplierQualityInformationDao;
import com.jaezi.bus.supplierQuality.dto.SupplierQualityInformationDto;
import com.jaezi.bus.supplierQuality.model.MidQmFeedbackTablePerson;
import com.jaezi.bus.supplierQuality.model.MidSupplierQualityInformationPerson;
import com.jaezi.bus.supplierQuality.model.SupplierQualityInformation;
import com.jaezi.bus.supplierQuality.vo.SupplierQualityInformationVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.FileUtil;
import io.minio.MinioClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.jaezi.common.constant.MinioConst.*;
import static com.jaezi.common.util.DateUtil.getCurrentDate;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 17:19
 * @description 供应商质量信息业务层
 */
@Service
public class SupplierQualityInformationService extends BaseService<SupplierQualityInformation, SupplierQualityInformationVo> {

    private SupplierQualityInformationDao supplierQualityInformationDao;

    private final MinioClient minioClient;

    private MidSupplierQualityInformationPersonDao midSupplierQualityInformationPersonDao;

    @Autowired
    public void setBaseDao(SupplierQualityInformationDao supplierQualityInformationDao, MidSupplierQualityInformationPersonDao midSupplierQualityInformationPersonDao) {
        super.setBaseDao(supplierQualityInformationDao);
        this.supplierQualityInformationDao = supplierQualityInformationDao;
        this.midSupplierQualityInformationPersonDao = midSupplierQualityInformationPersonDao;
    }

    public SupplierQualityInformationService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    /**
     * 删除之前先判断关联的是否有人员，有的话，先删除中间表信息，再删除
     *
     * @param id 被删除的信息id
     * @return int   删除成功数
     * @author hj
     * @date 2021/8/24
     * @since 1.0
     */
    public int deleteSupplierQualityInformation(Integer id) {
        List<MidQmFeedbackTablePerson> list = midSupplierQualityInformationPersonDao.getListBySupplierQualityInformationId(id);
        if (list.size() > 0) {
            midSupplierQualityInformationPersonDao.deleteListBysupplierQualityInformationId(id);
        }
        return supplierQualityInformationDao.delete(id);
    }

    /**
     * 文件下载
     *
     * @param response response对象
     * @param id       对象ID
     */
    public void down(HttpServletResponse response, Integer id) {
        SupplierQualityInformation supplierQualityInformation = supplierQualityInformationDao.getOneById(id);
        if (supplierQualityInformation != null) {
            String url = supplierQualityInformation.getUrl();
            String suffix = url.substring(url.lastIndexOf(".") + 1);
            FileUtil.downLoad(supplierQualityInformation.getUrl(), supplierQualityInformation.getReportName() + "." + suffix, response, minioClient, SUPPLIER_QUALITY_INFORMATION_DIR);
        }
    }

    /**
     * 查询所有供应商质量信息
     *
     * @param filter
     * @return SupplierQualityInformation>
     */
    public DataGrid<SupplierQualityInformation> findAll(Map<String, String> filter) {
        DataGrid<SupplierQualityInformation> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<SupplierQualityInformation> all = getListByVisible(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<SupplierQualityInformation> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<SupplierQualityInformation> list = getListByVisible(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    public List<SupplierQualityInformation> getListByVisible(Map<String, String> filter) {
        Integer userType = Integer.valueOf(filter.get("userType"));
        if (userType == 1) {
            return supplierQualityInformationDao.findByVisible(filter);
        }
        return supplierQualityInformationDao.findAll(filter);
    }

    /**
     * 添加QM反馈单
     *
     * @param supplierQualityInformationDto 供应商质量信息对象数据和可见人员数据
     * @return int     添加成功数
     * @author whj
     * @date 2021/8/19
     * @since 1.0
     */
    public int addSupplierQualityInformation(SupplierQualityInformationDto supplierQualityInformationDto) {
        SupplierQualityInformation supplierQualityInformation = new SupplierQualityInformation();
        BeanUtils.copyProperties(supplierQualityInformationDto, supplierQualityInformation);
        List<Integer> visiblePersonList = supplierQualityInformationDto.getVisiblePersonList();
        Integer visibleType = supplierQualityInformationDto.getVisible();
        supplierQualityInformation.setVisible(visibleType);
        supplierQualityInformation.setUpdateDate(String.valueOf(System.currentTimeMillis()));
        int addResult = supplierQualityInformationDao.add(supplierQualityInformation);
        if (addResult > 0) {
            if (visibleType == 0) {
                for (Integer personId : visiblePersonList) {
                    MidSupplierQualityInformationPerson midSupplierQualityInformationPerson = new MidSupplierQualityInformationPerson();
                    midSupplierQualityInformationPerson.setPersonId(personId);
                    midSupplierQualityInformationPerson.setSupplierQualityInformationId(supplierQualityInformation.getId());
                    midSupplierQualityInformationPersonDao.add(midSupplierQualityInformationPerson);
                }
            }
        }
        return addResult;
    }

    /**
     * 文件上传
     *
     * @param file        文件对象
     * @param minioClient minio客户端对象
     * @author whj
     * @date 2021/8/14
     * @since 1.0
     */
    public String upload(MultipartFile file, MinioClient minioClient) {
        // 文件夹名称
        String dir = SUPPLIER_QUALITY_INFORMATION_DIR + getCurrentDate() + "/";
        Map<String, String> result = FileUtil.uploadAndFileSiz(file, minioClient, dir);
        if (result != null) {
            return result.get(URL);
        }
        return null;
    }

    /**
     * 修改供应商质量信息，先查看对象中是否有可见性的修改，有的话就先删除之前的可见性，重新添加新的可见性
     *
     * @param supplierQualityInformationDto 供应商质量信息对象数据和可见人员数据
     * @return int  修改数
     * @author whj
     * @date 2021/8/19
     * @since 1.0
     */
    public int updateSupplierQualityInformation(SupplierQualityInformationDto supplierQualityInformationDto) {
        SupplierQualityInformation supplierQualityInformation = new SupplierQualityInformation();
        BeanUtils.copyProperties(supplierQualityInformationDto, supplierQualityInformation);
        Integer visible = supplierQualityInformationDto.getVisible();
        List<Integer> visiblePersonList = supplierQualityInformationDto.getVisiblePersonList();
        Integer id = supplierQualityInformationDto.getId();
        int result = midSupplierQualityInformationPersonDao.deleteListBysupplierQualityInformationId(id);
        if (result > 0) {
            if (visible == 0) {
                for (Integer personId : visiblePersonList) {
                    MidSupplierQualityInformationPerson midSupplierQualityInformationPerson = new MidSupplierQualityInformationPerson();
                    midSupplierQualityInformationPerson.setPersonId(personId);
                    midSupplierQualityInformationPerson.setSupplierQualityInformationId(id);
                    midSupplierQualityInformationPersonDao.add(midSupplierQualityInformationPerson);
                }
            }
        }
        return supplierQualityInformationDao.update(supplierQualityInformation);
    }
}
