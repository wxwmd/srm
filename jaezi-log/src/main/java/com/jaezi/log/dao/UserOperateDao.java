package com.jaezi.log.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.log.model.UserOperate;
import com.jaezi.log.vo.UserOperateVo;
import org.springframework.stereotype.Repository;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/7/14 14:48
 * @description
 */
@Repository
public interface UserOperateDao extends BaseDao<UserOperate, UserOperateVo> {
    /**
     * 转存用户操作日志
     *
     * @param userOperate 用户操作日志表名
     */
    void userOperateDump(String userOperate);

    /**
     * 删除用户操作日志
     * @return
     */
    int deleteUserOperate();
}
