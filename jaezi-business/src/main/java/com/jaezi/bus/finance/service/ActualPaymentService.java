package com.jaezi.bus.finance.service;

import com.jaezi.bus.finance.dao.ActualPaymentDao;
import com.jaezi.bus.finance.model.ActualPayment;
import com.jaezi.bus.finance.vo.ActualPaymentVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/6 9:41
 * @description
 * 实际付款情况的业务层
 */
@Service
public class ActualPaymentService extends BaseService<ActualPayment, ActualPaymentVo> {

    private ActualPaymentDao actualPaymentDao;

    @Autowired
    public void setBaseDao(ActualPaymentDao actualPaymentDao) {
        super.setBaseDao(actualPaymentDao);
        this.actualPaymentDao = actualPaymentDao;
    }
}
