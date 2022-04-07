package com.jaezi.bus.plan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.plan.dao.JunDemandPlanningDao;
import com.jaezi.bus.plan.model.JunDemandPlanning;
import com.jaezi.bus.plan.vo.FourteenDayDemandPlanningVo;
import com.jaezi.bus.plan.vo.JunDemandPlanningVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 六月需求计划逻辑层
 */

@Service
public class JunDemandPlanningService extends BaseService<JunDemandPlanning, JunDemandPlanningVo> {

    private JunDemandPlanningDao junDemandPlanningDao;

    @Autowired
    public void setBaseDao(JunDemandPlanningDao junDemandPlanningDao) {
        super.setBaseDao(junDemandPlanningDao);
        this.junDemandPlanningDao = junDemandPlanningDao;
    }

    /**
     * 查询所有六月数据
     *
     * @param filter
     * @return FourteenDayDemandPlanningVo>
     */
    @Override
    public DataGrid<JunDemandPlanningVo> getAll(Map<String, String> filter) {
        DataGrid<JunDemandPlanningVo> dg = new DataGrid<>();
        if (filter == null || filter.get("limit") == null || filter.get("page") == null) {
            List<JunDemandPlanningVo> all = getAllVos(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<JunDemandPlanningVo> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<JunDemandPlanningVo> list = getAllVos(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    public List<JunDemandPlanningVo> getAllVos(Map<String, String> filter) {
        List<JunDemandPlanningVo> junDemandPlanningVoList = junDemandPlanningDao.getAllVos(filter);
        for (JunDemandPlanningVo junDemandPlanningVo : junDemandPlanningVoList) {
            junDemandPlanningVo.setHobby(junDemandPlanningDao.getMaterialDay(junDemandPlanningVo.getId()));
        }
        return junDemandPlanningVoList;
    }
}
