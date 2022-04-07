package com.jaezi.bus.inventory.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.inventory.dao.OutsourcingSubcontractInventoryDao;
import com.jaezi.bus.inventory.model.OutsourcingSubcontractInventory;
import com.jaezi.bus.inventory.vo.OutsourcingSubcontractInventoryVo;
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
 * @date 2021/8/9 16:23
 * @description 外协分包库存业务层
 */
@Service
public class OutsourcingSubcontractInventoryService extends BaseService<OutsourcingSubcontractInventory, OutsourcingSubcontractInventoryVo> {

    private OutsourcingSubcontractInventoryDao outsourcingSubcontractInventoryDao;

    @Autowired
    public void setBaseDao(OutsourcingSubcontractInventoryDao outsourcingSubcontractInventoryDao) {
        super.setBaseDao(outsourcingSubcontractInventoryDao);
        this.outsourcingSubcontractInventoryDao = outsourcingSubcontractInventoryDao;
    }

    /**
     * 查询外协分包库存信息列表
     *
     * @param filter 过滤条件
     * @return OutsourcingSubcontractInventory>
     * @author wanghaojie
     * @date 2021/8/4
     * @since 1.0
     */
    public DataGrid<OutsourcingSubcontractInventory> findAll(Map<String, String> filter) {
        DataGrid<OutsourcingSubcontractInventory> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<OutsourcingSubcontractInventory> all = outsourcingSubcontractInventoryDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<OutsourcingSubcontractInventory> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<OutsourcingSubcontractInventory> list = outsourcingSubcontractInventoryDao.findAll(filter);
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
        List<OutsourcingSubcontractInventory> list = outsourcingSubcontractInventoryDao.findAll(filter);
        if (ObjectUtils.isEmpty(list)) {
            return;
        }
        ExcelUtil.export(response, list, "外协分包库存信息", "模板", OutsourcingSubcontractInventory.class);
    }
}
