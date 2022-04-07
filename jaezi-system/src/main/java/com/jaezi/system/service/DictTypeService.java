package com.jaezi.system.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.system.dao.DictTypeDao;
import com.jaezi.system.model.DictType;
import com.jaezi.system.vo.DictTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/15 15:49
 * @description 字典服务实现类
 */

@Service
public class DictTypeService extends BaseService<DictType, DictTypeVo> {

    private DictTypeDao dictTypeDao;

    @Autowired
    public void setBaseDao(DictTypeDao dictTypeDao) {
        super.setBaseDao(dictTypeDao);
        this.dictTypeDao = dictTypeDao;
    }

    /**
     * 查询所有字典列表
     *
     * @return 字典列表
     */
    public List<DictTypeVo> getAllVos() {
        return dictTypeDao.getAllVos(null);
    }

}
