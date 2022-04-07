package com.jaezi.bus.inventory.service;

import com.jaezi.bus.inventory.dao.ConsignmentGoodsSDTRParticularDao;
import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTRParticular;
import com.jaezi.bus.inventory.vo.ConsignmentGoodsSDTRParticularVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/11/15  14:57:03
 * @description
 */
@Service
public class ConsignmentGoodsSDTRParticularService extends BaseService<ConsignmentGoodsSDTRParticular, ConsignmentGoodsSDTRParticularVo> {

    private ConsignmentGoodsSDTRParticularDao consignmentGoodsSDTRParticularDao;

    @Autowired
    public void setBaseDao(ConsignmentGoodsSDTRParticularDao consignmentGoodsSDTRParticularDao) {
        super.setBaseDao(consignmentGoodsSDTRParticularDao);
        this.consignmentGoodsSDTRParticularDao = consignmentGoodsSDTRParticularDao;
    }

}
