package com.jaezi.synergia.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.synergia.dao.MidTechnicalInformationSupplierDao;
import com.jaezi.synergia.model.MidTechnicalInformationSupplier;
import com.jaezi.synergia.vo.MidTechnicalInformationSupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3  19:06:42
 * @description
 */
@Service
public class MidTechnicalInformationSupplierService extends BaseService<MidTechnicalInformationSupplier, MidTechnicalInformationSupplierVo> {

    private MidTechnicalInformationSupplierDao midTechnicalInformationSupplierDao;

    @Autowired
    public void setBaseDao(MidTechnicalInformationSupplierDao midTechnicalInformationSupplierDao) {
        super.setBaseDao(midTechnicalInformationSupplierDao);
        this.midTechnicalInformationSupplierDao = midTechnicalInformationSupplierDao;
    }

    /**
     * 根据技术资料交流id查询中间表集合
     * @since 1.0
     * @author whj
     * @date 2021/9/1
     * @param technicalInformationId   技术资料交流id
     * @return MidTechnicalInformationSupplier>    中间表集合
     */
    public List<MidTechnicalInformationSupplier> getListByTechnicalInformationId(Integer technicalInformationId) {
        return midTechnicalInformationSupplierDao.getListByTechnicalInformationId(technicalInformationId);
    }
}
