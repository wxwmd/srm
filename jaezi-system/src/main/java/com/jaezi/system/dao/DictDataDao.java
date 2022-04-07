package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.DictData;
import com.jaezi.system.vo.DictDataVo;
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
public interface DictDataDao extends BaseDao<DictData, DictDataVo> {
    /**
     * 根据字典类型或者状态找字典数据
     *
     * @param dictType 字典类型
     * @param status 字典状态
     * @return 字典数据
     * @date 2021/7/23
     */
    List<DictData> selectDictDataByTypeAndStatus(String dictType, Integer status);

    /**
     * 根据字典类型删除字典数据
     *
     * @param dictType 字典类型
     * @return 字典列表
     */
    int deleteDictDataByType(String dictType);
}
