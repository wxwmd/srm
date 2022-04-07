package com.jaezi.srminterface.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.srminterface.dao.FiledConfigDao;
import com.jaezi.srminterface.model.FiledConfig;
import com.jaezi.srminterface.vo.FiledConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 数据列配置逻辑层
 */
@Service
public class FiledConfigService extends BaseService<FiledConfig, FiledConfigVo> {
    private FiledConfigDao filedConfigDao;

    @Autowired
    private void setInterfaceDao(FiledConfigDao filedConfigDao) {
        super.setBaseDao(filedConfigDao);
        this.filedConfigDao = filedConfigDao;
    }
}
