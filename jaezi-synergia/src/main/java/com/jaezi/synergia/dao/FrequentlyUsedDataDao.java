package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.FrequentlyUsedData;
import com.jaezi.synergia.vo.FrequentlyUsedDataVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 技术图档信息数据访问对象
 */
@Repository
public interface FrequentlyUsedDataDao extends BaseDao<FrequentlyUsedData, FrequentlyUsedDataVo> {
    /**
     * 查询所有常用资料信息
     *
     * @param filter name 技术图档名称
     *               description 技术图档描述
     * @return
     */
    List<FrequentlyUsedData> findAll(Map<String, String> filter);

    /**
     * 根据常用资料ID集合查询常用资料列表
     *
     * @param idList 技术图档ID集合
     * @return
     */
    List<FrequentlyUsedData> findByIds(List<Integer> idList);

    /**
     * 根据常用资料ID集合查询常用资料列表
     *
     * @param filter name 技术图档名称
     *               description 技术图档描述
     * @return
     */
    List<FrequentlyUsedData> findByVisible(Map<String, String> filter);
}
