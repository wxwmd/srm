package com.jaezi.bus.inventory.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.inventory.dao.ConsignmentGoodsCloseNoticeLetterDao;
import com.jaezi.bus.inventory.model.ConsignmentGoodsCloseNoticeLetter;
import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTR;
import com.jaezi.bus.inventory.vo.ConsignmentGoodsCloseNoticeLetterVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10 18:04
 * @description 寄售物资结算通知单业务层
 */
@Service
public class ConsignmentGoodsCloseNoticeLetterService extends BaseService<ConsignmentGoodsCloseNoticeLetter, ConsignmentGoodsCloseNoticeLetterVo> {
    private ConsignmentGoodsCloseNoticeLetterDao consignmentGoodsCloseNoticeLetterDao;

    @Autowired
    public void setBaseDao(ConsignmentGoodsCloseNoticeLetterDao consignmentGoodsCloseNoticeLetterDao) {
        super.setBaseDao(consignmentGoodsCloseNoticeLetterDao);
        this.consignmentGoodsCloseNoticeLetterDao = consignmentGoodsCloseNoticeLetterDao;
    }

    /**
     * 查询所有寄售物资结算通知单
     *
     * @param filter
     * @return ConsignmentGoodsCloseNoticeLetter>
     */
    public DataGrid<ConsignmentGoodsCloseNoticeLetter> findAll(Map<String, String> filter) {
        DataGrid<ConsignmentGoodsCloseNoticeLetter> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<ConsignmentGoodsCloseNoticeLetter> all = consignmentGoodsCloseNoticeLetterDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<ConsignmentGoodsCloseNoticeLetter> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<ConsignmentGoodsCloseNoticeLetter> list = consignmentGoodsCloseNoticeLetterDao.findAll(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }
}
