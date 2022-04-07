package com.jaezi.info.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.info.model.MidMsgPerson;
import com.jaezi.info.vo.MidMsgPersonVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 新闻可见性访问对象
 */
@Repository
public interface MidMsgPersonDao extends BaseDao<MidMsgPerson, MidMsgPersonVo> {
    /**
     * 批量插入新闻可见性
     *
     * @param list 新闻可见性集合
     * @return int
     */
    int saveBath(List<MidMsgPerson> list);

    /**
     * 根据新闻id查询新闻可见性集合
     *
     * @param msgId 新闻ID
     * @return List<MidMsgPerson>
     */
    List<MidMsgPerson> getListByMsgId(Integer msgId);
}
