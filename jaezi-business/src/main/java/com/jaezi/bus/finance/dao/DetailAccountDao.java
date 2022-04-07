package com.jaezi.bus.finance.dao;

import com.jaezi.bus.finance.model.DetailAccount;
import com.jaezi.bus.finance.vo.DetailAccountVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/6 9:24
 * @description
 * 明细账的数据层
 */
@Repository
public interface DetailAccountDao extends BaseDao<DetailAccount, DetailAccountVo> {
}
