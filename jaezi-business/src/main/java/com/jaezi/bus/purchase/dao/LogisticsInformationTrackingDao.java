package com.jaezi.bus.purchase.dao;

import com.jaezi.bus.purchase.model.LogisticsInformationTracking;
import com.jaezi.bus.purchase.vo.LogisticsInformationTrackingVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 物流信息跟踪数据处理
 */

@Repository
public interface LogisticsInformationTrackingDao extends BaseDao<LogisticsInformationTracking, LogisticsInformationTrackingVo> {
    /**
     * 批量插入物流信息跟踪
     *
     * @param logisticsInformationTrackingList 物流信息跟踪信息集合
     * @return int
     */
    int save(List<LogisticsInformationTracking> logisticsInformationTrackingList);
}
