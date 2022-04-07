package com.jaezi.bus.supplierQuality.dao;

import com.jaezi.bus.supplierQuality.model.SupplierReport;
import com.jaezi.bus.supplierQuality.vo.SupplierReportVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/13 11:04
 * @description 供应商报告数据层
 */
@Repository
public interface SupplierReportDao extends BaseDao<SupplierReport, SupplierReportVo> {
    /**
     * 查询所有供应商报告
     *
     * @param filter supplierCode 供应商
     *               userType 用户类型
     *               reportName 报告名称
     *               reportDescription 报告描述
     *               materialNumber 物料号
     *               reportNumber 报告单号
     *               reportType 报告类型
     *               startTime 开始时间
     *               endTime 结束时间
     * @return SupplierQualityInformation>
     * @author whj
     * @date 2021/8/20
     * @since 1.0
     */
    List<SupplierReport> findAll(Map<String, String> filter);
}
