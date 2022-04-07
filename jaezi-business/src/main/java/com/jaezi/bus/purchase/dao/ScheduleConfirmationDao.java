package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.ScheduleConfirmation;
import com.jaezi.bus.purchase.vo.ScheduleConfirmationVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 再计划时间确认数据处理
 */

@Repository
public interface ScheduleConfirmationDao extends BaseDao<ScheduleConfirmation, ScheduleConfirmationVo> {
    /**
     * 批量插入再计划时间确认
     *
     * @param supplierBasicDataList 再计划时间确认集合
     * @return int
     */
    int save(List<ScheduleConfirmation> supplierBasicDataList);
}
