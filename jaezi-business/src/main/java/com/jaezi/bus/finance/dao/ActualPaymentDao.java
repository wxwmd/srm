package com.jaezi.bus.finance.dao;

import com.jaezi.bus.finance.model.ActualPayment;
import com.jaezi.bus.finance.vo.ActualPaymentVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/6 9:22
 * @description
 * 实际付款情况的数据层
 */
@Repository
public interface ActualPaymentDao extends BaseDao<ActualPayment, ActualPaymentVo> {
}
