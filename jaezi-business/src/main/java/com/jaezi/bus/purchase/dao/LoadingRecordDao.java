package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.LoadingDocument;
import com.jaezi.bus.purchase.model.LoadingRecord;
import com.jaezi.bus.purchase.vo.LoadingDocumentVo;
import com.jaezi.bus.purchase.vo.LoadingRecordVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/23 14:32
 * @description 装车单记录的数据层
 */
@Repository
public interface LoadingRecordDao extends BaseDao<LoadingRecord, LoadingRecordVo> {

    /**
     * 装车单采购查询(分页)
     *
     * @param filter         userType 用户类型
     *                       colorStatus 颜色状态
     *                       supplierCode 供应商
     *                       pOrder 采购订单号
     *                       plant 工厂
     *                       startTime 开始时间
     *                       entTime 结束时间
     * @param purchaseIdList 采购订单ID集合
     * @return LoadingDocument>
     */
    List<LoadingDocument> getLoadingPurchase(Map<String, String> filter, List<String> purchaseIdList);

    /**
     * 根据装车单ID 删除相关联装车单明细
     *
     * @param loadingDocumentId 装车单ID
     * @return int
     */
    int deleteByLoadingDocumentId(Integer loadingDocumentId);
}
