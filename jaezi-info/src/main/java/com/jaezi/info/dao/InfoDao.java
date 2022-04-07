package com.jaezi.info.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.info.model.Info;
import com.jaezi.info.vo.InfoVo;
import org.springframework.stereotype.Repository;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 消息信息数据访问对象
 */
@Repository
public interface InfoDao extends BaseDao<Info, InfoVo> {

    /**
     * 更新未读状态
     *
     * @param id       信息交流ID
     * @param realName 昵称
     * @return int
     */
    int updateReadStatus(Integer id, String realName);

    /**
     * 更新索赔信息
     *
     * @param claimId 索赔ID
     * @param infoId  信息交流ID
     * @return int
     */
    int updateClaim(Integer claimId, Integer infoId);

    /**
     * 更新删除状态
     *
     * @param info     信息交流对象
     * @param realName 昵称
     * @return int
     */
    int updateStatus(Info info, String realName);
}
