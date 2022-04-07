package com.jaezi.srminterface.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.srminterface.model.FiledConfig;
import com.jaezi.srminterface.vo.FiledConfigVo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 数据列配置实体类
 */
@Repository
public interface FiledConfigDao extends BaseDao<FiledConfig, FiledConfigVo> {
    /**
     * 根据tableId删除数据列
     *
     * @param id 数据表配置ID
     * @return int
     */
    int deleteByTableId(Serializable id);

    /**
     * 查询所有数据列
     *
     * @param filter filedName 列名
     *               tableName 表名
     * @return
     */
    List<FiledConfig> getAll(Map<String, String> filter);
}
