package com.jaezi.srminterface.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.srminterface.model.WeChatUser;
import com.jaezi.srminterface.vo.WeChatUserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 用户微信绑定信息持久层
 */

@Repository
public interface WeChatUserDao extends BaseDao<WeChatUser, WeChatUserVo> {

    /**
     * 获取关注微信服务号用户个数
     *
     * @return
     */
    int count();

    /**
     * 批量添加关注微信服务号用户
     *
     * @param weChatUserList 关注用户集合
     * @return
     */
    int addBatch(List<WeChatUser> weChatUserList);

    /**
     * 批量更新关注微信服务号用户
     *
     * @param weChatUserList 关注用户集合
     * @return
     */
    int updateBatch(List<WeChatUser> weChatUserList);

    /**
     * 根据最大ID获取对应的OPENID
     *
     * @return
     */
    String getOpenIdByMaxId();

    /**
     * 获取最大ID
     *
     * @return
     */
    int getMaxId();
}
