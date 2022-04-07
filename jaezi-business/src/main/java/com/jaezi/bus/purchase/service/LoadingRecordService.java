package com.jaezi.bus.purchase.service;


import com.jaezi.bus.purchase.dao.LoadingRecordDao;
import com.jaezi.bus.purchase.model.LoadingRecord;
import com.jaezi.bus.purchase.vo.LoadingRecordVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/23 16:02
 * @description 装车单记录业务层
 */
@Service
public class LoadingRecordService extends BaseService<LoadingRecord, LoadingRecordVo> {

    private LoadingRecordDao loadingRecordDao;

    @Autowired
    public void setBaseDao(LoadingRecordDao loadingRecordDao) {
        super.setBaseDao(loadingRecordDao);
        this.loadingRecordDao = loadingRecordDao;
    }

    /**
     * 删除相关联装车单明细
     *
     * @param loadingDocumentId
     * @return int
     */
    public int deleteByLoadingDocumentId(Integer loadingDocumentId) {
        return loadingRecordDao.deleteByLoadingDocumentId(loadingDocumentId);
    }
}
