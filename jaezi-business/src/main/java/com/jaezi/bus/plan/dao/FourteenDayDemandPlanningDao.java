package com.jaezi.bus.plan.dao;

import com.jaezi.bus.plan.dto.FourteenDayDemandPlanningDto;
import com.jaezi.bus.plan.model.FourteenDayDemandPlanning;
import com.jaezi.bus.plan.vo.FourteenDayDemandPlanningVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 十四天数据访问
 */

@Repository
public interface FourteenDayDemandPlanningDao extends BaseDao<FourteenDayDemandPlanning, FourteenDayDemandPlanningVo> {
    /**
     * 批量插入十四天需求计划
     *
     * @param list 十四天需求计划集合
     * @return int
     */
    int saveBath(List<FourteenDayDemandPlanning> list);

    /**
     * 获取双周供需核查需求计划
     *
     * @param id 双周供需核查ID
     * @return FourteenDayDemandPlanningDto>
     */
    List<FourteenDayDemandPlanningDto> getMaterialDay(Integer id);
}
