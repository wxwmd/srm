package com.jaezi.srminterface.dao;

import com.jaezi.srminterface.model.FiledConfig;
import com.jaezi.srminterface.model.MaterialSupplyDemand;
import com.jaezi.srminterface.model.TableConfig;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 动态数据表持久层
 */
@Repository
public interface InterfaceServerDao {

    /**
     * 动态创建表
     *
     * @param table           表名
     * @param tableComment    表注释
     * @param tableConfigList 表数据列配置集合
     * @param isPk            是否有主键
     * @return
     */
    int createTable(String table, String tableComment, List<FiledConfig> tableConfigList, Long isPk);

    /**
     * 动态表写入数据
     *
     * @param table         表名
     * @param filedNameList 表数据列名配置集合
     * @param mapList       数据集合
     * @return
     */
    int saveTable(String table, List<String> filedNameList, List<Map<String, Object>> mapList);

    /**
     * 动态表更新数据
     *
     * @param table           表名
     * @param tableConfigList 表配置集合
     * @param isPk            是否有主键
     * @return
     */
    int updateTable(String table, List<TableConfig> tableConfigList, Long isPk);

    /**
     * 根据表名称获取表列名
     *
     * @param table 表名
     * @return String
     */
    List<String> getTableFiledByTableName(String table);

    /**
     * 添加列名
     *
     * @param table       表名
     * @param tableConfig 表数据列配置
     * @return int
     */
    int alterFiled(String table, FiledConfig tableConfig);

    /**
     * 批量插入物料表
     *
     * @param materialInterfaceList 物料集合
     * @return int
     */
    int saveMaterialBath(List<Object> materialInterfaceList);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 单条用户信息
     */
    Object getUserByUsername(String username);

    /**
     * 批量插入用户信息
     *
     * @param list 用户信息集合
     * @return int
     */
    int saveUserBath(List<Object> list);

    /**
     * 批量插入供应商信息
     *
     * @param list 供应商信息集合
     * @return
     */
    int saveSupplierBath(List<Object> list);

    /**
     * 批量插入采购订单信息
     *
     * @param list 采购订单信息集合
     * @return
     */
    int savePurchaseBath(List<Object> list);

    /**
     * 获取采购订单最大ID
     *
     * @return
     */
    int getPurchaseMaxId();

    /**
     * 获取标准物资开票最大ID
     *
     * @return
     */
    int getStandardInvoiceOutMaxId();

    /**
     * 批量插入开票信息
     *
     * @param list 开票信息集合
     * @return
     */
    int saveStandardInvoiceOutBath(List<Object> list);

    /**
     * 根据用户ID获取供应商表的用户ID
     *
     * @param userId 用户ID
     */
    Integer getSupplierByUserId(Object userId);

    /**
     * 删除供需差异数据
     *
     * @return int
     */
    int deleteSupplyDiff();

    /**
     * 批量插入供需差异数据
     *
     * @param list 供需差异数据
     * @return
     */
    int saveSupplyDiffBath(List<Object> list);

    /**
     * 获取物料供需数据
     *
     * @param sqlContent 自定义SQL
     * @return List<MaterialSupplyDemand>
     */
    List<MaterialSupplyDemand> getMaterialSupplyData(String sqlContent);

    /**
     * 手动执行sql
     *
     * @param sqlContent 自定义SQL
     * @return int
     */
    int sqlManualRun(String sqlContent);
}
