package com.jaezi.bus.plan.vo;

import com.jaezi.bus.plan.dto.FourteenDayDemandPlanningDto;
import com.jaezi.bus.plan.dto.JunDemandPlanningDto;
import com.jaezi.bus.plan.model.JunDemandPlanning;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 六月需求计划扩展类
 */

public class JunDemandPlanningVo extends JunDemandPlanning {
    private List<JunDemandPlanningDto> hobby;

    public List<JunDemandPlanningDto> getHobby() {
        return hobby;
    }

    public void setHobby(List<JunDemandPlanningDto> hobby) {
        this.hobby = hobby;
    }

}
