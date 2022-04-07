package com.jaezi.bus.salesreturn.service;

import com.jaezi.bus.salesreturn.dao.ReturnConfirmationDao;
import com.jaezi.bus.salesreturn.model.ReturnConfirmation;
import com.jaezi.bus.salesreturn.vo.ReturnConfirmationVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 退货确认数据逻辑层
 */

@Service
public class ReturnConfirmationService extends BaseService<ReturnConfirmation, ReturnConfirmationVo> {

    private ReturnConfirmationDao returnConfirmationDao;

    @Autowired
    public void setBaseDao(ReturnConfirmationDao returnConfirmationDao) {
        super.setBaseDao(returnConfirmationDao);
        this.returnConfirmationDao = returnConfirmationDao;
    }
}
