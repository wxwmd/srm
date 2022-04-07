package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.ClaimNote;
import com.jaezi.synergia.model.Info;
import com.jaezi.synergia.vo.ClaimNoteVo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 索赔信息数据访问对象
 */
@Repository
public interface ClaimNoteDao extends BaseDao<ClaimNote, ClaimNoteVo> {

    /**
     * 根据回复ID查询信息列表
     *
     * @param replyId  回复ID
     * @param realName 昵称
     * @return
     */
    List<Info> getInfoByInfoId(int replyId, String realName);

    /**
     * 根据索赔信息ID获取索赔信息
     *
     * @param id 索赔信息ID
     * @return
     */
    ClaimNoteVo getOneById(Serializable id);

    /**
     * 根据信息交流ID删除信息
     *
     * @param infoId 信息交流ID
     * @return
     */
    int deleteInfoByInfoId(Integer infoId);

    /**
     * 根据回复ID删除信息
     *
     * @param replyId 回复ID
     * @return
     */
    int deleteInfoByReplyId(Integer replyId);
}
