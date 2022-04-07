package com.jaezi.synergia.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.synergia.dao.ParamDesignDao;
import com.jaezi.synergia.model.ParamDesign;
import com.jaezi.synergia.vo.ParamDesignVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 物料参数设计管理逻辑层
 */

@Service
public class ParamDesignService extends BaseService<ParamDesign, ParamDesignVo> {

    private ParamDesignDao paramDesignDao;

    @Autowired
    public void setBaseDao(ParamDesignDao paramDesignDao) {
        super.setBaseDao(paramDesignDao);
        this.paramDesignDao = paramDesignDao;
    }

}
