package com.jaezi.log.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.log.dao.UserOperateDao;
import com.jaezi.log.model.UserOperate;
import com.jaezi.log.vo.UserOperateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/07/23 14:46
 * @description 用户操作日志逻辑层
 */
@Service
public class UserOperateService extends BaseService<UserOperate, UserOperateVo> {

    private UserOperateDao userOperateDao;

    @Autowired
    public void setBaseDao(UserOperateDao userOperateDao) {
        super.setBaseDao(userOperateDao);
        this.userOperateDao = userOperateDao;
    }
}
