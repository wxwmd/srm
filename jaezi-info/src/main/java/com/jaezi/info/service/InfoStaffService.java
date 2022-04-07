package com.jaezi.info.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.info.dao.InfoStaffDao;
import com.jaezi.info.model.InfoStaff;
import com.jaezi.info.vo.InfoStaffVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/22 15:49
 * @description 消息抄送人信息接口实现类
 */
@Service
public class InfoStaffService extends BaseService<InfoStaff, InfoStaffVo> {

    public InfoStaffDao infoStaffDao;

    @Autowired
    public void setBaseDao(InfoStaffDao infoStaffDao) {
        super.setBaseDao(infoStaffDao);
        this.infoStaffDao = infoStaffDao;
    }

}
