package com.jaezi.srminterface.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.srminterface.model.SqlConfig;
import com.jaezi.srminterface.vo.SqlConfigVo;
import org.springframework.stereotype.Repository;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 自定义sql配置持久层
 */
@Repository
public interface SqlConfigDao extends BaseDao<SqlConfig, SqlConfigVo> {
}
