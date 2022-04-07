package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.MidFrequentlyUsedDataPerson;
import com.jaezi.synergia.vo.MidFrequentlyUsedDataPersonVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3 10:56
 * @description
 */
@Repository
public interface MidFrequentlyUsedDataPersonDao extends BaseDao<MidFrequentlyUsedDataPerson, MidFrequentlyUsedDataPersonVo> {

    /**
     * 根据常用资料id集合删除
     *
     * @param frequentlyUsedDataIds 常用资料id集合
     * @return int
     * @author wanghaojie
     * @date 2021/8/4
     * @since 1.0
     */
    int deleteByVisiblePersonIds(List<Integer> frequentlyUsedDataIds);

    /**
     * 根据常用资料id查询中间表集合
     *
     * @param frequentlyUsedDataId 常用资料id
     * @return MidFrequentlyUsedDataPerson>    中间表集合
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    List<MidFrequentlyUsedDataPerson> getListByFrequentlyUsedDataId(Integer frequentlyUsedDataId);

    /**
     * 根据常用资料ID删除可见性中间表
     *
     * @param frequentlyUsedDataId 常用资料ID
     * @return
     */
    int deleteByFrequentlyUsedDataId(Integer frequentlyUsedDataId);
}
