package com.jaezi.info.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.info.dao.MidMsgPersonDao;
import com.jaezi.info.model.MidMsgPerson;
import com.jaezi.info.vo.MidMsgPersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/22 15:49
 * @description 新闻可见性接口实现类
 */
@Service
public class MidMsgPersonService extends BaseService<MidMsgPerson, MidMsgPersonVo> {

    private MidMsgPersonDao midMsgPersonDao;

    @Autowired
    public void setBaseDao(MidMsgPersonDao midMsgPersonDao) {
        super.setBaseDao(midMsgPersonDao);
        this.midMsgPersonDao = midMsgPersonDao;
    }

    public List<MidMsgPerson> getListByMsgId(Integer msgId) {
        return midMsgPersonDao.getListByMsgId(msgId);
    }
}
