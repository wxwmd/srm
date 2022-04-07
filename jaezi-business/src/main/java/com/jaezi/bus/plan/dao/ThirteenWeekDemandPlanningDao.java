package com.jaezi.bus.plan.dao;

import com.jaezi.bus.plan.model.ThirteenWeekDemandPlanning;
import com.jaezi.bus.plan.vo.ThirteenWeekDemandPlanningVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 十三周数据访问
 */

@Repository
public interface ThirteenWeekDemandPlanningDao extends BaseDao<ThirteenWeekDemandPlanning, ThirteenWeekDemandPlanningVo> {
    /**
     * 批量插入十三周数据
     *
     * @param list 十三周数据集合
     * @return int
     */
    int saveBath(List<ThirteenWeekDemandPlanning> list);

    /**
     * 获取十三周数据
     *
     * @param id 十三周数据ID
     * @return FourteenDayDemandPlanningDto>
     */
    List<ThirteenWeekDemandPlanningVo> getMaterialDay(Integer id);
}
