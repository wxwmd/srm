package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.MidTechnicalInformationSupplier;
import com.jaezi.synergia.vo.MidTechnicalInformationSupplierVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3  19:09:17
 * @description
 */
@Repository
public interface MidTechnicalInformationSupplierDao extends BaseDao<MidTechnicalInformationSupplier, MidTechnicalInformationSupplierVo> {

    /**
     * 根据技术资料id删除技术资料与人员中间表
     * @since 1.0
     * @author yx
     * @date 2021年8月9日15:11:46
     * @param informationIds 技术资料id集合
     * @return int
     */
    int deleteByInformationIds(List<Integer> informationIds);

    /**
     * 根据技术资料交流id查询中间表集合
     * @since 1.0
     * @author whj
     * @date 2021/9/1
     * @param technicalInformationId   技术资料交流id
     * @return MidTechnicalInformationSupplier>    中间表集合
     */
    List<MidTechnicalInformationSupplier> getListByTechnicalInformationId(Integer technicalInformationId);

    /**
     * 根据技术资料id删除资料人员中间表信息
     * @since 1.0
     * @author whj
     * @date 2021/9/7
     * @param informationId    资料id
     * @return int   删除数量
     */
    int deleteByInformationId(Integer informationId);
}
