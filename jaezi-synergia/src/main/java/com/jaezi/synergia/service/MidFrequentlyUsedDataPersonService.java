package com.jaezi.synergia.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.synergia.dao.MidFrequentlyUsedDataPersonDao;
import com.jaezi.synergia.model.MidFrequentlyUsedDataPerson;
import com.jaezi.synergia.vo.MidFrequentlyUsedDataPersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/3 10:54
 * @description
 */
@Service
public class MidFrequentlyUsedDataPersonService extends BaseService<MidFrequentlyUsedDataPerson, MidFrequentlyUsedDataPersonVo> {

    private MidFrequentlyUsedDataPersonDao midFrequentlyUsedDataPersonDao;

    @Autowired
    public void setBaseDao(MidFrequentlyUsedDataPersonDao midFrequentlyUsedDataPersonDao) {
        super.setBaseDao(midFrequentlyUsedDataPersonDao);
        this.midFrequentlyUsedDataPersonDao = midFrequentlyUsedDataPersonDao;
    }


    /**
     * 根据常用资料id查询中间表集合
     * @since 1.0
     * @author whj
     * @date 2021/9/1
     * @param frequentlyUsedDataId   常用资料id
     * @return MidFrequentlyUsedDataPerson>    中间表集合
     */
    public List<MidFrequentlyUsedDataPerson> getListByFrequentlyUsedDataId(Integer frequentlyUsedDataId) {
        return midFrequentlyUsedDataPersonDao.getListByFrequentlyUsedDataId(frequentlyUsedDataId);
    }
}
