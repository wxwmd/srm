package com.jaezi.bus.plan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.plan.dao.FourteenDayDemandPlanningDao;
import com.jaezi.bus.plan.model.FourteenDayDemandPlanning;
import com.jaezi.bus.plan.vo.FourteenDayDemandPlanningVo;
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
 * @description 十四天逻辑层
 */

@Service
public class FourteenDayDemandPlanningService<V> extends BaseService<FourteenDayDemandPlanning, FourteenDayDemandPlanningVo> {
    private FourteenDayDemandPlanningDao fourteenDayDemandPlanningDao;

    @Autowired
    public void setBaseDao(FourteenDayDemandPlanningDao fourteenDayDemandPlanningDao) {
        super.setBaseDao(fourteenDayDemandPlanningDao);
        this.fourteenDayDemandPlanningDao = fourteenDayDemandPlanningDao;
    }

    /**
     * 查询所有十四天数据
     *
     * @param filter
     * @return FourteenDayDemandPlanningVo>
     */
    @Override
    public DataGrid<FourteenDayDemandPlanningVo> getAll(Map<String, String> filter) {
        DataGrid<FourteenDayDemandPlanningVo> dg = new DataGrid<>();
        if (filter == null || filter.get("limit") == null || filter.get("page") == null) {
            List<FourteenDayDemandPlanningVo> all = getAllVos(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<FourteenDayDemandPlanningVo> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<FourteenDayDemandPlanningVo> list = getAllVos(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    public List<FourteenDayDemandPlanningVo> getAllVos(Map<String, String> filter) {
        List<FourteenDayDemandPlanningVo> fourteenDayDemandPlanningVoList = fourteenDayDemandPlanningDao.getAllVos(filter);
        for (FourteenDayDemandPlanningVo fourteenDayDemandPlanningVo : fourteenDayDemandPlanningVoList) {
            fourteenDayDemandPlanningVo.setHobby(fourteenDayDemandPlanningDao.getMaterialDay(fourteenDayDemandPlanningVo.getId()));
        }
        return fourteenDayDemandPlanningVoList;
    }

}
