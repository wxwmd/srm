package com.jaezi.bus.plan.vo;

import com.jaezi.bus.plan.dto.FourteenDayDemandPlanningDto;
import com.jaezi.bus.plan.model.FourteenDayDemandPlanning;
import com.jaezi.bus.purchase.model.LoadingRecord;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 十四天扩展类
 */

public class FourteenDayDemandPlanningVo extends FourteenDayDemandPlanning {

    private List<FourteenDayDemandPlanningDto> hobby;

    public List<FourteenDayDemandPlanningDto> getHobby() {
        return hobby;
    }

    public void setHobby(List<FourteenDayDemandPlanningDto> hobby) {
        this.hobby = hobby;
    }
}
