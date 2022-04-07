package com.jaezi.bus.supplierQuality.service;

import com.jaezi.bus.supplierQuality.dao.MidSupplierQualityInformationPersonDao;
import com.jaezi.bus.supplierQuality.model.MidQmFeedbackTablePerson;
import com.jaezi.bus.supplierQuality.model.MidSupplierQualityInformationPerson;
import com.jaezi.bus.supplierQuality.vo.MidSupplierQualityInformationPersonVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 16:49
 * @description
 * 供应商质量信息人员中间表业务层
 */
@Service
public class MidSupplierQualityInformationPersonService extends BaseService<MidSupplierQualityInformationPerson, MidSupplierQualityInformationPersonVo> {

    private MidSupplierQualityInformationPersonDao midSupplierQualityInformationPersonDao;

    @Autowired
    public void setBaseDao(MidSupplierQualityInformationPersonDao midSupplierQualityInformationPersonDao) {
        super.setBaseDao(midSupplierQualityInformationPersonDao);
        this.midSupplierQualityInformationPersonDao = midSupplierQualityInformationPersonDao;
    }

    /**
     * 根据供应商质量信息id查询集合
     * @since 1.0
     * @author whj
     * @date 2021/8/19
     * @param supplierQualityInformationId  供应商质量信息id
     * @return MidQmFeedbackTablePerson>   中间表集合
     */
    public List<MidQmFeedbackTablePerson> getListBySupplierQualityInformationId(Integer supplierQualityInformationId){
        return midSupplierQualityInformationPersonDao.getListBySupplierQualityInformationId(supplierQualityInformationId);
    }

}
