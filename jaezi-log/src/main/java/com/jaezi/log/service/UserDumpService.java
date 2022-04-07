package com.jaezi.log.service;

import com.jaezi.common.util.DateUtil;
import com.jaezi.log.dao.UserAuthDao;
import com.jaezi.log.dao.UserOperateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation Copyright by iuyy.net
 * @date 2021/07/23 14:46
 * @description 用户日志转存逻辑层
 */
@Service
public class UserDumpService {

    private UserAuthDao userAuthDao;
    private UserOperateDao userOperateDao;

    @Autowired
    public void setBaseDao(UserAuthDao userAuthDao, UserOperateDao userOperateDao) {
        this.userAuthDao = userAuthDao;
        this.userOperateDao = userOperateDao;
    }

    public String getCron() {
        return "0 0 0 1/7 * ? ";
    }

    public void userDumpTask() {
        String toDay = DateUtil.getCurrentDate("YYYYMMdd");

        String userAuth = "sys_user_auth" + toDay;
        String userOperate = "sys_user_operate" + toDay;

        //每七天转存一次数据
        userAuthDao.userAuthDump(userAuth);
        userOperateDao.userOperateDump(userOperate);
        userAuthDao.deleteUserAuth();
        userOperateDao.deleteUserOperate();
    }
}
