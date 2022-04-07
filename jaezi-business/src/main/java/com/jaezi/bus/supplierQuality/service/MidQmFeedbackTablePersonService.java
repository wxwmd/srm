package com.jaezi.bus.supplierQuality.service;

import com.jaezi.bus.supplierQuality.dao.MidQmFeedbackTablePersonDao;
import com.jaezi.bus.supplierQuality.model.MidQmFeedbackTablePerson;
import com.jaezi.bus.supplierQuality.vo.MidQmFeedbackTablePersonVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/19 11:27
 * @description QM反馈单人员关系的业务层
 */
@Service
public class MidQmFeedbackTablePersonService extends BaseService<MidQmFeedbackTablePerson, MidQmFeedbackTablePersonVo> {

    private MidQmFeedbackTablePersonDao midQmFeedbackTablePersonDao;

    @Autowired
    public void setBaseDao(MidQmFeedbackTablePersonDao midQmFeedbackTablePersonDao) {
        super.setBaseDao(midQmFeedbackTablePersonDao);
        this.midQmFeedbackTablePersonDao = midQmFeedbackTablePersonDao;
    }

    /**
     * 根据qm反馈单id查询集合
     *
     * @param qmFeedbackTableId qm反馈单id
     * @return MidQmFeedbackTablePerson>   中间表集合
     * @author whj
     * @date 2021/8/19
     * @since 1.0
     */
    public List<MidQmFeedbackTablePerson> getListByQmFeedbackTableId(Integer qmFeedbackTableId) {
        return midQmFeedbackTablePersonDao.getListByQmFeedbackTableId(qmFeedbackTableId);
    }
}
