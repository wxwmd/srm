package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.Aggregat;
import com.jaezi.synergia.vo.AggregatVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/30 15:39
 * @description 索赔信息数据访问对象
 */
@Repository
public interface AggregatDao extends BaseDao<Aggregat, AggregatVo> {

    /**
     * 根据物料号获取总成件组件信息
     *
     * @param filter materialNumber 物料号
     * @return Aggregat>
     */
    List<Aggregat> getAllByMaterial(Map<String, String> filter);

}
