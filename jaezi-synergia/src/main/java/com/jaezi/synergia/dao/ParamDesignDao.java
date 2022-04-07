package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.ParamDesign;
import com.jaezi.synergia.vo.ParamDesignVo;
import org.springframework.stereotype.Repository;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 物料参数设计管理数据访问层
 */
@Repository
public interface ParamDesignDao extends BaseDao<ParamDesign, ParamDesignVo> {

}
