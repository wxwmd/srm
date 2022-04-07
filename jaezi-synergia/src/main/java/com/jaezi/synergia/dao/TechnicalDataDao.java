package com.jaezi.synergia.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.synergia.model.TechnicalData;
import com.jaezi.synergia.vo.TechnicalDataVo;
import org.apache.ibatis.annotations.Param;
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
public interface TechnicalDataDao extends BaseDao<TechnicalData, TechnicalDataVo> {

    /**
     * 根据id查找附件集合
     *
     * @param id 技术图档资料ID
     * @return
     */
    String getAccessoryInfo(String id);

    /**
     * 查询所有技术图档
     *
     * @param filter materialNumber 物料号
     *               realName 昵称
     * @return
     */
    List<TechnicalData> findAll(Map<String, String> filter);

    /**
     * 根据技术图档id集合查询技术图档列表
     *
     * @param dataIdList 技术图档id
     * @return
     */
    List<TechnicalData> findByIds(List<Integer> dataIdList);

    /**
     * 根据技术图档id集合查询技术图档列表
     *
     * @param filter materialNumber 物料号
     *               userId 用户ID
     * @return
     */
    List<TechnicalData> findByVisible(Map<String, String> filter);

    /**
     * 根据物料号，凭证号，凭证版本号查询技术图档
     *
     * @param materialNumber      物料号
     * @param certificateNumber   凭证号
     * @param certificateVersions 凭证版本号
     * @return TechnicalData
     * @author whj
     * @date 2021/10/27
     * @since 1.0
     */
    TechnicalData findByMaNumberAndCerNumberAndCerVersions(@Param("materialNumber") String materialNumber, @Param("certificateNumber") String certificateNumber, @Param("certificateVersions") String certificateVersions);
}
