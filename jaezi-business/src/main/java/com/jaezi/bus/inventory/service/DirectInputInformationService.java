package com.jaezi.bus.inventory.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.inventory.dao.DirectInputInformationDao;
import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTR;
import com.jaezi.bus.inventory.model.DirectInputInformation;
import com.jaezi.bus.inventory.vo.DirectInputInformationVo;
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
 * @date 2021/8/9 16:21
 * @description 直送入库信息业务层
 */
@Service
public class DirectInputInformationService extends BaseService<DirectInputInformation, DirectInputInformationVo> {

    private DirectInputInformationDao directInputInformationDao;

    @Autowired
    public void setBaseDao(DirectInputInformationDao directInputInformationDao) {
        super.setBaseDao(directInputInformationDao);
        this.directInputInformationDao = directInputInformationDao;
    }

    /**
     * 查询直送入库信息列表
     *
     * @param filter 过滤条件
     * @return DirectInputInformation>
     * @author wanghaojie
     * @date 2021/8/4
     * @since 1.0
     */
    public DataGrid<DirectInputInformation> findAll(Map<String, String> filter) {
        DataGrid<DirectInputInformation> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<DirectInputInformation> all = directInputInformationDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<DirectInputInformation> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<DirectInputInformation> list = directInputInformationDao.findAll(filter);
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
        List<DirectInputInformation> list = directInputInformationDao.findAll(filter);
        if (ObjectUtils.isEmpty(list)) {
            return;
        }
        ExcelUtil.export(response, list, "直送入库信息", "模板", DirectInputInformation.class);
    }
}
