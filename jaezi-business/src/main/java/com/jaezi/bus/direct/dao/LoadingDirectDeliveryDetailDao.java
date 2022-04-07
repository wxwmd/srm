package com.jaezi.bus.direct.dao;

import com.jaezi.bus.direct.model.LoadingDirectDeliveryDetail;
import com.jaezi.bus.direct.vo.LoadingDirectDeliveryDetailVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/17 16:28
 * @description
 * 直达装车单明细数据层
 */
@Repository
public interface LoadingDirectDeliveryDetailDao extends BaseDao<LoadingDirectDeliveryDetail, LoadingDirectDeliveryDetailVo> {
}
