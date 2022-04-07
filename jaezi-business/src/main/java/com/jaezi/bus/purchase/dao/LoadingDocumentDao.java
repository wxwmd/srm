package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.LoadingDocument;
import com.jaezi.bus.purchase.vo.LoadingDocumentVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/5 17:37
 * @description 装货单的数据层
 */
@Repository
public interface LoadingDocumentDao extends BaseDao<LoadingDocument, LoadingDocumentVo> {

    /**
     * 查询所有装车单
     *
     * @param filter purchaseOrder 订单号
     *               lineItem 行项目
     *               receivingWindow 收货窗口
     *               shippingExplain 装运说明
     *               loadingDate 装车日期
     *               loadingDocumentDateStartTime 装车单生成日期
     *               planDeliveryDate 计划到货日期
     *               loadingNumber 装车单号
     *               loadingWay 装车方式
     *               licensePlateNumber 车牌号
     *               factory 工厂
     *               loadingShipmentDateStartTime 装车发运日期开始时间
     *               loadingShipmentDateEndTime 装车发运日期结束时间
     *               logisticsContactInformation 物流联系方式
     *               supplierCode 供应商
     *               loadingDate 装车日期
     *               planDeliveryDate 计划到货日期
     *               userType 用户类型
     *               startTime 开始时间
     *               entTime 结束时间
     * @return LoadingDocument>
     * <p>
     * 用作查询订单、装车单信息，装车单打印信息
     */
    List<LoadingDocument> findAll(Map<String, String> filter);

    /**
     * 根据装车单id获取装车单详情
     *
     * @param loadingDocumentId 装车单id
     * @return LoadingDocumentVo 装车单详情对象
     * @author whj
     * @date 2021/8/24
     * @since 1.0
     */
    LoadingDocumentVo getLoadingDocumentDetails(Integer loadingDocumentId);
}
