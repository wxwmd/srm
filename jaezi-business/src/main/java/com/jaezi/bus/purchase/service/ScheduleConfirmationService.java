package com.jaezi.bus.purchase.service;

import com.jaezi.bus.purchase.dao.ScheduleConfirmationDao;
import com.jaezi.bus.purchase.model.ScheduleConfirmation;
import com.jaezi.bus.purchase.vo.ScheduleConfirmationVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 再计划时间确认逻辑层
 */

@Service
public class ScheduleConfirmationService extends BaseService<ScheduleConfirmation, ScheduleConfirmationVo> {

    private ScheduleConfirmationDao scheduleConfirmationDao;

    @Autowired
    public void setBaseDao(ScheduleConfirmationDao scheduleConfirmationDao) {
        super.setBaseDao(scheduleConfirmationDao);
        this.scheduleConfirmationDao = scheduleConfirmationDao;
    }

    /**
     * 根据条件导出excel到浏览器
     *
     * @param filter 采购订单
     * @return 操作是否成功
     */
    public void export(HttpServletResponse response, Map<String, String> filter) throws Exception {
        List<ScheduleConfirmationVo> scheduleConfirmationVoList = scheduleConfirmationDao.getAllVos(filter);
        if (ObjectUtils.isEmpty(scheduleConfirmationVoList)) {
            return;
        }
        ExcelUtil.export(response, scheduleConfirmationVoList, "再计划时间确认信息", "模板", ScheduleConfirmation.class);
    }

}
