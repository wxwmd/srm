package com.jaezi.bus.financialAffairs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.financialAffairs.dao.StandardInvoiceOutInfoDao;
import com.jaezi.bus.financialAffairs.model.StandardInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.vo.StandardInvoiceOutInfoVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/27  16:14:59
 * @description
 */
@Service
public class StandardInvoiceOutInfoService extends BaseService<StandardInvoiceOutInfo, StandardInvoiceOutInfoVo> {

    private StandardInvoiceOutInfoDao standardInvoiceOutInfoDao;

    @Autowired
    public void setBaseDao(StandardInvoiceOutInfoDao standardInvoiceOutInfoDao) {
        super.setBaseDao(standardInvoiceOutInfoDao);
        this.standardInvoiceOutInfoDao = standardInvoiceOutInfoDao;
    }

    /**
     * 查询所有标准物资开票
     *
     * @param filter plant
     *               startTime
     * @return StandardInvoiceOut>
     */
    public DataGrid<StandardInvoiceOutInfo> findAll(Map<String, String> filter) {
        DataGrid<StandardInvoiceOutInfo> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<StandardInvoiceOutInfo> all = standardInvoiceOutInfoDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<StandardInvoiceOutInfo> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<StandardInvoiceOutInfo> list = standardInvoiceOutInfoDao.findAll(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }
}
