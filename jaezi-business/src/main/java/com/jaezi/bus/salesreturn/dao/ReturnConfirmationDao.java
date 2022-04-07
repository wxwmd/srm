package com.jaezi.bus.salesreturn.dao;

import com.jaezi.bus.salesreturn.model.ReturnConfirmation;
import com.jaezi.bus.salesreturn.vo.ReturnConfirmationVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 退货确认数据访问层
 */

@Repository
public interface ReturnConfirmationDao extends BaseDao<ReturnConfirmation, ReturnConfirmationVo> {

    /**
     * 批量插入退货确认
     *
     * @param list 退货确认数据集合
     * @return int
     */
    int saveBath(List<ReturnConfirmation> list);
}
