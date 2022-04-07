package com.jaezi.srminterface.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.srminterface.dao.FiledConfigDao;
import com.jaezi.srminterface.dao.TableConfigDao;
import com.jaezi.srminterface.model.TableConfig;
import com.jaezi.srminterface.vo.TableConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 动态表配置逻辑层
 */

@Service
public class TableConfigService extends BaseService<TableConfig, TableConfigVo> {

    private TableConfigDao tableConfigDao;
    private FiledConfigDao filedConfigDao;

    @Autowired
    private void setInterfaceDao(TableConfigDao tableConfigDao, FiledConfigDao filedConfigDao) {
        super.setBaseDao(tableConfigDao);
        this.tableConfigDao = tableConfigDao;
        this.filedConfigDao = filedConfigDao;
    }

    public int delete(Serializable id) {
        filedConfigDao.deleteByTableId(id);
        String tableName = tableConfigDao.getOneById(id).getTableName();

        StringBuffer tableNameSb = new StringBuffer();
        tableNameSb.append("`");
        tableNameSb.append(tableName);
        tableNameSb.append("`");
        tableConfigDao.dropTable(tableNameSb.toString());

        return tableConfigDao.delete(id);
    }

    public String getCommentsByTableName(String tableName) {
        return tableConfigDao.getCommentsByTableName(tableName);
    }

    public String getCron() {
//        return "*/59 * * * * ?";
//        return "0 */1 * * * ?";
        return "0 0 1 * * ?";
    }

    public String getPath() {
        return tableConfigDao.getPath().get("config");
    }
}
