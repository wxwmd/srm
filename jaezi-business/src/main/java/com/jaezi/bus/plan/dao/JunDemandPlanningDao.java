package com.jaezi.bus.plan.dao;

import com.jaezi.bus.plan.dto.FourteenDayDemandPlanningDto;
import com.jaezi.bus.plan.dto.JunDemandPlanningDto;
import com.jaezi.bus.plan.model.JunDemandPlanning;
import com.jaezi.bus.plan.vo.JunDemandPlanningVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 六月需求计划数据访问
 */

@Repository
public interface JunDemandPlanningDao extends BaseDao<JunDemandPlanning, JunDemandPlanningVo> {
    /**
     * 批量插入六月需求计划
     *
     * @param list 六月需求计划集合
     * @return int
     */
    int saveBath(List<JunDemandPlanning> list);

    /**
     * 获取六月需求计划
     *
     * @param id 六月需求计划ID
     * @return FourteenDayDemandPlanningDto>
     */
    List<JunDemandPlanningDto> getMaterialDay(Integer id);
}
