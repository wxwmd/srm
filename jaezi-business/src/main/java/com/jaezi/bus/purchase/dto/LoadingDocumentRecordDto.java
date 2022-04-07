package com.jaezi.bus.purchase.dto;

import com.jaezi.bus.purchase.model.LoadingDocument;
import com.jaezi.bus.purchase.model.LoadingRecord;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/23 16:28
 * @description
 * 装车单和装车单记录的dto
 */
public class LoadingDocumentRecordDto extends LoadingDocument {

    private List<LoadingRecord> loadingRecords;

    public List<LoadingRecord> getLoadingRecords() {
        return loadingRecords;
    }

    public void setLoadingRecords(List<LoadingRecord> loadingRecords) {
        this.loadingRecords = loadingRecords;
    }
}
