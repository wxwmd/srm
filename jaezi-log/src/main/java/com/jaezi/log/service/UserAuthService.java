package com.jaezi.log.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.log.dao.UserAuthDao;
import com.jaezi.log.model.UserAuth;
import com.jaezi.log.vo.UserAuthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/07/23 14:46
 * @description 用户登录日志逻辑层
 */
@Service
public class UserAuthService extends BaseService<UserAuth, UserAuthVo> {

    private UserAuthDao userAuthDao;

    @Autowired
    public void setBaseDao(UserAuthDao userAuthDao) {
        super.setBaseDao(userAuthDao);
        this.userAuthDao = userAuthDao;
    }
}
