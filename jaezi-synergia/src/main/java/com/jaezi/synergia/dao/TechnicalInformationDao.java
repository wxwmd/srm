package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.TechnicalInformation;
import com.jaezi.synergia.vo.TechnicalInformationVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3  9:57:38
 * @description
 */
@Repository
public interface TechnicalInformationDao extends BaseDao<TechnicalInformation, TechnicalInformationVo> {

    /**
     * 根据技术资料id列表查询技术资料
     *
     * @param idList 技术资料id列表
     * @return FrequentlyUsedData> 技术资料对象
     * @author yx
     * @date 2021年8月9日14:17:16
     * @since 1.0
     */
    List<TechnicalInformation> findByIds(List<Integer> idList);

    /**
     * 查询技术资料
     *
     * @param filter name 资料名称
     *               description 资料描述
     *               startTime 开始时间
     *               endTime 结束时间
     * @return TechnicalInformation> 技术资料对象
     * @author yx
     * @date 2021年8月9日14:57:35
     * @since 1.0
     */
    List<TechnicalInformation> findAll(Map<String, String> filter);

    /**
     * 根据可见性获取技术图档
     *
     * @param filter name 资料名称
     *               description 资料描述
     *               startTime 开始时间
     *               endTime 结束时间
     * @return
     */
    List<TechnicalInformation> findByVisible(Map<String, String> filter);
}
