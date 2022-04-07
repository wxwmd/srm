package com.jaezi.bus.finance.dao;

import com.jaezi.bus.finance.model.BookBalance;
import com.jaezi.bus.finance.vo.BookBalanceVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/6 9:23
 * @description
 * 账面余额的数据层
 */
@Repository
public interface BookBalanceDao extends BaseDao<BookBalance, BookBalanceVo> {
}
