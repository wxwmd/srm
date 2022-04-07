package com.jaezi.bus.supplierQuality.dao;

import com.jaezi.bus.supplierQuality.model.QmFeedbackTable;
import com.jaezi.bus.supplierQuality.vo.QmFeedbackTableVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/13 11:06
 * @description QM反馈单
 */
@Repository
public interface QmFeedbackTableDao extends BaseDao<QmFeedbackTable, QmFeedbackTableVo> {

    /**
     * 根据页面选择的条件查询所有返回的是QmFeedbackTable对象
     *
     * @param filter visible 可见性
     *               problemOdd 问题单号
     *               feedbackTheme 反馈主题
     *               feedbackPeople 反馈人
     *               feedbackDepartment 反馈部门
     *               receiptType 单据类型
     *               url 文件地址
     *               finishedProductCode 成品代码
     *               startTime 开始时间
     *               endTime 结束时间
     * @return QmFeedbackTable>
     * @author whj
     * @date 2021/8/20
     * @since 1.0
     */
    List<QmFeedbackTable> findAll(Map<String, String> filter);

    /**
     * 当用户是供应商时查询的方法
     *
     * @param filter problemOdd 问题单号
     *               feedbackTheme 反馈主题
     *               feedbackPeople 反馈人
     *               feedbackDepartment 反馈部门
     *               receiptType 单据类型
     *               url 文件地址
     *               finishedProductCode 成品代码
     *               startTime 开始时间
     *               endTime 结束时间
     * @return QmFeedbackTable
     * @author whj
     * @date 2021/8/20
     * @since 1.0
     */
    List<QmFeedbackTable> findByVisible(Map<String, String> filter);
}
