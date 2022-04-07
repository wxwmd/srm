package com.jaezi.bus.inventory.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.inventory.dao.ConsignmentGoodsSDTRDao;
import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTR;
import com.jaezi.bus.inventory.vo.ConsignmentGoodsSDTRVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9 16:19
 * @description 寄售物资结收发存业务层
 */
@Service
public class ConsignmentGoodsSDTRService extends BaseService<ConsignmentGoodsSDTR, ConsignmentGoodsSDTRVo> {

    private ConsignmentGoodsSDTRDao consignmentGoodsSDTRDao;

    @Autowired
    public void setBaseDao(ConsignmentGoodsSDTRDao consignmentGoodsSDTRDao) {
        super.setBaseDao(consignmentGoodsSDTRDao);
        this.consignmentGoodsSDTRDao = consignmentGoodsSDTRDao;
    }


    /**
     * 查询寄售物资结收发存
     *
     * @param filter 过滤条件
     * @return ConsignmentGoodsSDTR>
     * @author wanghaojie
     * @date 2021/8/4
     * @since 1.0
     */
    public DataGrid<ConsignmentGoodsSDTR> findAll(Map<String, String> filter) {
        DataGrid<ConsignmentGoodsSDTR> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<ConsignmentGoodsSDTR> all = consignmentGoodsSDTRDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<ConsignmentGoodsSDTR> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<ConsignmentGoodsSDTR> list = consignmentGoodsSDTRDao.findAll(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 导出excel
     *
     * @param filter 传入的查询条件
     * @return void
     */
    public void export(HttpServletResponse response, Map<String, String> filter) throws Exception {
        List<ConsignmentGoodsSDTR> list = consignmentGoodsSDTRDao.findAll(filter);
        if (ObjectUtils.isEmpty(list)) {
            return;
        }
        ExcelUtil.export(response, list, "寄售物资结收发存信息", "模板", ConsignmentGoodsSDTR.class);
    }
}
