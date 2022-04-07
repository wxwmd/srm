package com.jaezi.bus.supplierQuality.dao;

import com.jaezi.bus.supplierQuality.model.SupplierQualityInformation;
import com.jaezi.bus.supplierQuality.vo.SupplierQualityInformationVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/20 16:43
 * @description 供应商质量信息数据层
 */
@Repository
public interface SupplierQualityInformationDao extends BaseDao<SupplierQualityInformation, SupplierQualityInformationVo> {

    /**
     * 根据页面选择的条件查询所有返回的是SupplierQualityInformation对象
     *
     * @param filter reportName 报告名称
     *               visible 可见性
     *               reportDescription 报告描述
     *               url 文件地址
     *               startTime 开始时间
     *               entTime 结束时间
     * @return SupplierQualityInformation>
     * @author whj
     * @date 2021/8/20
     * @since 1.0
     */
    List<SupplierQualityInformation> findAll(Map<String, String> filter);

    /**
     * 当用户是供应商时查询的方法
     *
     * @param filter reportName 报告名称
     *               reportDescription 报告描述
     *               url 文件地址
     *               startTime 开始时间
     *               entTime 结束时间
     * @return SupplierQualityInformation>
     * @author whj
     * @date 2021/8/20
     * @since 1.0
     */
    List<SupplierQualityInformation> findByVisible(Map<String, String> filter);
}
