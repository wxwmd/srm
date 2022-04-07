package com.jaezi.bus.finance.dao;

import com.jaezi.bus.finance.model.RateRedInvoice;
import com.jaezi.bus.finance.vo.RateRedInvoiceVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/25 18:59
 * @description
 * 返利红字发票数据层
 */
@Repository
public interface RateRedInvoiceDao extends BaseDao<RateRedInvoice, RateRedInvoiceVo> {
}
