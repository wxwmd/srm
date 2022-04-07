package com.jaezi.synergia.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.synergia.dao.MidTechnicalDataPersonDao;
import com.jaezi.synergia.model.MidTechnicalDataPerson;
import com.jaezi.synergia.vo.MidTechnicalDataPersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/4 14:55
 * @description
 */
@Service
public class MidTechnicalDataPersonService extends BaseService<MidTechnicalDataPerson, MidTechnicalDataPersonVo> {

    private MidTechnicalDataPersonDao midTechnicalDataPersonDao;

    @Autowired
    public void setBaseDao(MidTechnicalDataPersonDao midTechnicalDataPersonDao) {
        super.setBaseDao(midTechnicalDataPersonDao);
        this.midTechnicalDataPersonDao = midTechnicalDataPersonDao;
    }

    /**
     * 根据技术图档id查询中间表集合
     *
     * @param technicalDataId 技术图档id
     * @return MidTechnicalDataPerson>    中间表集合
     * @author whj
     * @date 2021/9/1
     * @since 1.0
     */
    public List<MidTechnicalDataPerson> getListByTechnicalDataId(Integer technicalDataId) {
        return midTechnicalDataPersonDao.getListByTechnicalDataId(technicalDataId);
    }
}
