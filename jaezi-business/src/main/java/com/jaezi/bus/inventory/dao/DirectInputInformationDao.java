package com.jaezi.bus.inventory.dao;

import com.jaezi.bus.inventory.model.DirectInputInformation;
import com.jaezi.bus.inventory.vo.DirectInputInformationVo;
import com.jaezi.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9 15:04
 * @description 直送入库信息数据层
 */
@Repository
public interface DirectInputInformationDao extends BaseDao<DirectInputInformation, DirectInputInformationVo> {

    /**
     * 查询所有直送入库信息
     *
     * @param filter materialName 物料名称
     *               materialDescription 物料描述
     *               qty 数量
     *               nonstopNumber 直达编号
     *               startTime 开始时间
     *               entTime 结束时间
     *               userType 用户类型
     * @return DirectInputInformation>
     */
    List<DirectInputInformation> findAll(Map<String, String> filter);
}
