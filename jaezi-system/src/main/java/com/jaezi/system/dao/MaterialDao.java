package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.Material;
import com.jaezi.system.vo.MaterialVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/5/19 19:13
 * @description 物料数据访问层
 */

@Repository
public interface MaterialDao extends BaseDao<Material, MaterialVo> {
    /**
     * 批量插入
     * @param list 物料列表
     * @return int
     */
    int bathSave(List<Material> list);
}
