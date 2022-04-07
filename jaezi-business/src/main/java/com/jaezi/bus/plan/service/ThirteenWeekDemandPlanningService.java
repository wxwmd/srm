package com.jaezi.bus.plan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.plan.dao.ThirteenWeekDemandPlanningDao;
import com.jaezi.bus.plan.model.ThirteenWeekDemandPlanning;
import com.jaezi.bus.plan.vo.ThirteenWeekDemandPlanningVo;
import com.jaezi.bus.plan.vo.ThirteenWeekDemandPlanningVo;
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
 * @description 十三周逻辑层
 */

@Service
public class ThirteenWeekDemandPlanningService extends BaseService<ThirteenWeekDemandPlanning, ThirteenWeekDemandPlanningVo> {

    private ThirteenWeekDemandPlanningDao thirteenWeekDemandPlanningDao;

    @Autowired
    public void setBaseDao(ThirteenWeekDemandPlanningDao thirteenWeekDemandPlanningDao) {
        super.setBaseDao(thirteenWeekDemandPlanningDao);
        this.thirteenWeekDemandPlanningDao = thirteenWeekDemandPlanningDao;
    }

    /**
     * 查询所有十三周数据
     *
     * @param filter
     * @return FourteenDayDemandPlanningVo>
     */
    @Override
    public DataGrid<ThirteenWeekDemandPlanningVo> getAll(Map<String, String> filter) {
        DataGrid<ThirteenWeekDemandPlanningVo> dg = new DataGrid<>();
        if (filter == null || filter.get("limit") == null || filter.get("page") == null) {
            List<ThirteenWeekDemandPlanningVo> all = getAllVos(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<ThirteenWeekDemandPlanningVo> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<ThirteenWeekDemandPlanningVo> list = getAllVos(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    public List<ThirteenWeekDemandPlanningVo> getAllVos(Map<String, String> filter) {
        List<ThirteenWeekDemandPlanningVo> ThirteenWeekDemandPlanningVoList = thirteenWeekDemandPlanningDao.getAllVos(filter);
        for (ThirteenWeekDemandPlanningVo thirteenWeekDemandPlanningVo : ThirteenWeekDemandPlanningVoList) {
            thirteenWeekDemandPlanningVo.setHobby(thirteenWeekDemandPlanningDao.getMaterialDay(thirteenWeekDemandPlanningVo.getId()));
        }
        return ThirteenWeekDemandPlanningVoList;
    }

}
