package com.jaezi.bus.plan.vo;

import com.jaezi.bus.plan.model.ThirteenWeekDemandPlanning;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 十三周扩展类
 */

public class ThirteenWeekDemandPlanningVo extends ThirteenWeekDemandPlanning {
    private List<ThirteenWeekDemandPlanningVo> hobby;

    public List<ThirteenWeekDemandPlanningVo> getHobby() {
        return hobby;
    }

    public void setHobby(List<ThirteenWeekDemandPlanningVo> hobby) {
        this.hobby = hobby;
    }

}
