package com.jaezi.bus.supplierQuality.dao;

import com.jaezi.bus.supplierQuality.model.MidQmFeedbackTablePerson;
import com.jaezi.bus.supplierQuality.model.MidSupplierQualityInformationPerson;
import com.jaezi.bus.supplierQuality.vo.MidSupplierQualityInformationPersonVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 16:41
 * @description 供应商质量信息人员中间表数据层
 */
@Repository
public interface MidSupplierQualityInformationPersonDao extends BaseDao<MidSupplierQualityInformationPerson, MidSupplierQualityInformationPersonVo> {

    /**
     * 根据供应商质量信息id查询中间表集合
     *
     * @param supplierQualityInformationId 供应商质量信息id
     * @return MidQmFeedbackTablePerson>
     * @author whj
     * @date 2021/8/20
     * @since 1.0
     */
    List<MidQmFeedbackTablePerson> getListBySupplierQualityInformationId(Integer supplierQualityInformationId);

    /**
     * 根据供应商质量信息id删除中间表信息
     *
     * @param supplierQualityInformationId 供应商质量信息id
     * @return int
     * @author whj
     * @date 2021/8/20
     * @since 1.0
     */
    int deleteListBysupplierQualityInformationId(Integer supplierQualityInformationId);
}
