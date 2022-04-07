package com.jaezi.srminterface.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.srminterface.dao.InterfaceServerDao;
import com.jaezi.srminterface.dao.SqlConfigDao;
import com.jaezi.srminterface.model.SqlConfig;
import com.jaezi.srminterface.vo.SqlConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 自定义sql配置逻辑层
 */

@Service
public class SqlConfigService extends BaseService<SqlConfig, SqlConfigVo> {

    private SqlConfigDao sqlConfigDao;
    private InterfaceServerDao interfaceServerDao;

    @Autowired
    private void setInterfaceDao(SqlConfigDao sqlConfigDao, InterfaceServerDao interfaceServerDao) {
        super.setBaseDao(sqlConfigDao);
        this.sqlConfigDao = sqlConfigDao;
        this.interfaceServerDao = interfaceServerDao;
    }

    /**
     * 手动执行sql
     *
     * @param sqlConfig 对象
     * @return 操作是否成功
     */
    public int manualRun(SqlConfig sqlConfig) {
        try {
            return interfaceServerDao.sqlManualRun(sqlConfig.getSqlContent());
        } catch (Exception e) {
            return -1;
        }
    }
}
