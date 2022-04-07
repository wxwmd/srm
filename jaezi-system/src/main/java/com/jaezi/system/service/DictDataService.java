package com.jaezi.system.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.system.dao.DictDataDao;
import com.jaezi.system.model.DictData;
import com.jaezi.system.vo.DictDataVo;
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
public class DictDataService extends BaseService<DictData, DictDataVo> {

    private DictDataDao dictDataDao;

    @Autowired
    public void setBaseDao(DictDataDao dictDataDao) {
        super.setBaseDao(dictDataDao);
        this.dictDataDao = dictDataDao;
    }

    /**
     * 根据字典类型获或者状态取字典数据
     * @param dictType 字典类型
     * @param status 字典状态
     * @return 字典列表
     */
    public List<DictData> selectDictDataByTypeAndStatus(String dictType, Integer status) {
        return dictDataDao.selectDictDataByTypeAndStatus(dictType, status);
    }

    /**
     * 根据字典类型删除字典数据
     *
     * @param dictType 字典类型
     * @return 字典列表
     */
    public int deleteDictDataByType(String dictType) {
        return dictDataDao.deleteDictDataByType(dictType);
    }

}
