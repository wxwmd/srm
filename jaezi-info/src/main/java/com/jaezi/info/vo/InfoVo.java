package com.jaezi.info.vo;

import com.jaezi.info.model.Info;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/22 15:49
 * @description 消息信息扩展类
 */

public class InfoVo extends Info {
    /**
     * 信息ID
     */
    private Integer infoId;
    /**
     * 索赔ID
     */
    private Integer claimId;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }
}
