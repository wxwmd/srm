package com.jaezi.bus.purchase.service;

import com.jaezi.bus.purchase.dao.LogisticsInformationTrackingDao;
import com.jaezi.bus.purchase.model.LogisticsInformationTracking;
import com.jaezi.bus.purchase.vo.LogisticsInformationTrackingVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 物流信息跟踪逻辑层
 */

@Service
public class LogisticsInformationTrackingService extends BaseService<LogisticsInformationTracking, LogisticsInformationTrackingVo> {

    private LogisticsInformationTrackingDao logisticsInformationTrackingDao;

    @Autowired
    public void setBaseDao(LogisticsInformationTrackingDao logisticsInformationTrackingDao) {
        super.setBaseDao(logisticsInformationTrackingDao);
        this.logisticsInformationTrackingDao = logisticsInformationTrackingDao;
    }

}
