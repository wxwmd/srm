package com.jaezi.bus.supplierQuality.dao;

import com.jaezi.bus.supplierQuality.model.MidQmFeedbackTablePerson;
import com.jaezi.bus.supplierQuality.vo.MidQmFeedbackTablePersonVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/19 11:18
 * @description QM反馈单人员关系的数据层
 */
@Repository
public interface MidQmFeedbackTablePersonDao extends BaseDao<MidQmFeedbackTablePerson, MidQmFeedbackTablePersonVo> {

    /**
     * 根据QM反馈单Id查询qm反馈单人员中间表集合
     *
     * @param qmFeedbackTableId qm反馈单id
     * @return MidQmFeedbackTablePerson>    中间表集合
     * @author whj
     * @date 2021/8/19
     * @since 1.0
     */
    List<MidQmFeedbackTablePerson> getListByQmFeedbackTableId(Integer qmFeedbackTableId);


    /**
     * 根据qm反馈单id删除中间表数据
     *
     * @param qmFeedBackTableId qm反馈单id
     * @return int   删除数
     * @author whj
     * @date 2021/8/19
     * @since 1.0
     */
    int deleteListByQmFeedbackTableId(Integer qmFeedBackTableId);

}
