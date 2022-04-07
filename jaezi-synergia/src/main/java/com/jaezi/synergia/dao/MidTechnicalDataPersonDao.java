package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;

import com.jaezi.synergia.model.MidTechnicalDataPerson;
import com.jaezi.synergia.vo.MidTechnicalDataPersonVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/4 14:56
 * @description
 */
@Repository
public interface MidTechnicalDataPersonDao extends BaseDao<MidTechnicalDataPerson, MidTechnicalDataPersonVo> {

    /**
     * 根据可见人员id集合删除
     *
     * @param visiblePersonList 可见人员id集合
     * @return int
     * @author wanghaojie
     * @date 2021/8/4
     * @since 1.0
     */
    int deleteByTechnicalDataIds(List<Integer> visiblePersonList);

    /**
     * 根据技术图档id查询中间表集合
     *
     * @param technicalDataId 技术图档id
     * @return MidTechnicalDataPerson>    中间表集合
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    List<MidTechnicalDataPerson> getListByTechnicalDataId(Integer technicalDataId);

    /**
     * 根据技术图档id删除技术图档和人员中间表
     *
     * @param technicalDataId 技术图档id
     * @return int    删除数量
     * @author whj
     * @date 2021/9/7
     * @since 1.0
     */
    int deleteByTechnicalDataId(Integer technicalDataId);
}
