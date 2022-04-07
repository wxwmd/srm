package com.jaezi.bus.direct.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.direct.dao.LoadingDirectDeliveryDao;
import com.jaezi.bus.direct.model.LoadingDirectDelivery;
import com.jaezi.bus.direct.vo.LoadingDirectDeliveryVo;
import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTR;
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
 * @date 2021/8/17 17:06
 * @description 直送装车单业务层
 */
@Service
public class LoadingDirectDeliveryService extends BaseService<LoadingDirectDelivery, LoadingDirectDeliveryVo> {

    private LoadingDirectDeliveryDao loadingDirectDeliveryDao;

    @Autowired
    public void setBaseDao(LoadingDirectDeliveryDao loadingDirectDeliveryDao) {
        super.setBaseDao(loadingDirectDeliveryDao);
        this.loadingDirectDeliveryDao = loadingDirectDeliveryDao;
    }

    /**
     * 查询直送装车单列表
     *
     * @param filter supplierCode 供应商编号
     *               userType 用户类型
     *               status 状态
     *               plant 工厂
     *               arrivalDateStartTime 到货日期开始时间
     *               licensePlateNumber 车牌号
     *               phone 电话
     *               startTime 开始时间
     *               endTime 结束时间
     * @return ConsignmentGoodsSDTR>
     * @author wanghaojie
     * @date 2021/8/4
     * @since 1.0
     */
    public DataGrid<LoadingDirectDelivery> findAll(Map<String, String> filter) {
        DataGrid<LoadingDirectDelivery> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<LoadingDirectDelivery> all = loadingDirectDeliveryDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<LoadingDirectDelivery> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<LoadingDirectDelivery> list = loadingDirectDeliveryDao.findAll(filter);
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
        List<LoadingDirectDelivery> list = loadingDirectDeliveryDao.findAll(filter);
        if (ObjectUtils.isEmpty(list)) {
            return;
        }
        ExcelUtil.export(response, list, "直达装车单信息", "模板", LoadingDirectDelivery.class);
    }
}
