package com.jaezi.bus.supplierQuality.dto;

import com.jaezi.bus.supplierQuality.model.QmFeedbackTable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/19 11:39
 * @description
 */
public class QmFeedbackTableDto extends QmFeedbackTable {

    /**
     * 可见人员集合
     */
    private List<Integer> visiblePersonList;


    public List<Integer> getVisiblePersonList() {
        return visiblePersonList;
    }

    public void setVisiblePersonList(List<Integer> visiblePersonList) {
        this.visiblePersonList = visiblePersonList;
    }
}
