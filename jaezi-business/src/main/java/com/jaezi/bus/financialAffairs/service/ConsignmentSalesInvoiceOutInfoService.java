package com.jaezi.bus.financialAffairs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.financialAffairs.dao.ConsignmentSalesInvoiceOutInfoDao;
import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.vo.ConsignmentSalesInvoiceOutInfoVo;
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
 * @date 2021/8/31  9:24:27
 * @description
 */
@Service
public class ConsignmentSalesInvoiceOutInfoService extends BaseService<ConsignmentSalesInvoiceOutInfo, ConsignmentSalesInvoiceOutInfoVo> {

    private ConsignmentSalesInvoiceOutInfoDao consignmentSalesInvoiceOutInfoDao;

    @Autowired
    public void setBaseDao(ConsignmentSalesInvoiceOutInfoDao consignmentSalesInvoiceOutInfoDao) {
        super.setBaseDao(consignmentSalesInvoiceOutInfoDao);
        this.consignmentSalesInvoiceOutInfoDao = consignmentSalesInvoiceOutInfoDao;
    }

    /**
     * 查询寄售物资开票信息
     *
     * @param filter 过滤条件
     * @return OutInvoice>
     * @author yx
     * @date 2021年8月10日 18:12:45
     * @since 1.0
     */
    public DataGrid<ConsignmentSalesInvoiceOutInfo> findAll(Map<String, String> filter) {
        DataGrid<ConsignmentSalesInvoiceOutInfo> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<ConsignmentSalesInvoiceOutInfo> all = consignmentSalesInvoiceOutInfoDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<ConsignmentSalesInvoiceOutInfo> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<ConsignmentSalesInvoiceOutInfo> list = consignmentSalesInvoiceOutInfoDao.findAll(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }
}
