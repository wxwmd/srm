package com.jaezi.log.dao;


import com.jaezi.common.base.BaseDao;
import com.jaezi.log.model.UserAuth;
import com.jaezi.log.vo.UserAuthVo;
import org.springframework.stereotype.Repository;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/7/14 14:48
 * @description
 */
@Repository
public interface UserAuthDao extends BaseDao<UserAuth, UserAuthVo> {
    /**
     * 转存用户登录日志
     *
     * @param userAuth 用户登录日志表名
     */
    void userAuthDump(String userAuth);

    /**
     * 删除用户登录日志
     *
     * @param
     * @return int
     */
    int deleteUserAuth();
}
