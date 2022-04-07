package com.jaezi.srminterface.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.srminterface.model.TableConfig;
import com.jaezi.srminterface.vo.TableConfigVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 动态表配置持久层
 */
@Repository
public interface TableConfigDao extends BaseDao<TableConfig, TableConfigVo> {

    /**
     * 获取所有数据表配置
     *
     * @param filter tableName 表名
     * @return
     */
    List<TableConfig> getAll(Map<String, String> filter);

    /**
     * 根据表名获取表注释
     *
     * @param tableName 表名
     * @return
     */
    String getCommentsByTableName(String tableName);

    /**
     * 获取配置文件夹路径
     *
     * @return
     */
    Map<String, String> getPath();

    /**
     * 根据表名删除表
     *
     * @param tableName 表名
     * @return
     */
    int dropTable(String tableName);
}
