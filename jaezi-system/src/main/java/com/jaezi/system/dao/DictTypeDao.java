package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.DictData;
import com.jaezi.system.model.DictType;
import com.jaezi.system.vo.DictTypeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2020/4/16 17:12
 * @description 字典数据访问对象
 */
@Repository
public interface DictTypeDao extends BaseDao<DictType, DictTypeVo> {

}
