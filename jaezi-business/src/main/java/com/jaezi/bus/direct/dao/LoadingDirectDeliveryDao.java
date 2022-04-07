package com.jaezi.bus.direct.dao;

import com.jaezi.bus.direct.model.LoadingDirectDelivery;
import com.jaezi.bus.direct.vo.LoadingDirectDeliveryVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/17 16:28
 * @description 直达装车单数据层
 */
@Repository
public interface LoadingDirectDeliveryDao extends BaseDao<LoadingDirectDelivery, LoadingDirectDeliveryVo> {

    /**
     * 查询所有直送入库信息
     *
     * @param filter supplierCode 供应商编号
     *               userType 用户类型
     *               status 状态
     *               plant 工厂
     *               arrivalDateStartTime 到货日期开始时间
     *               licensePlateNumber 车牌号
     *               phone 电话
     *               startTime 开始时间
     *               endTime 结束时间
     * @return LoadingDirectDelivery>
     */
    List<LoadingDirectDelivery> findAll(Map<String, String> filter);
}
