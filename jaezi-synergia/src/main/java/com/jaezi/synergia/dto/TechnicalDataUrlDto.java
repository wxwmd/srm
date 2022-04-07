package com.jaezi.synergia.dto;

import com.jaezi.common.base.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/10/27 16:13
 * @description
 */
public class TechnicalDataUrlDto extends BaseModel {

    /**
     * 可见人员集合
     */
    private List<Integer> visiblePersonList;

    /**
     * 可见性 0：部分供应商，1：全部供应商，2：企业
     */
    private Integer visible;

    /**
     * 文件集合
     */
    private List<Map<String,String>> fileNames;

    public List<Integer> getVisiblePersonList() {
        return visiblePersonList;
    }

    public void setVisiblePersonList(List<Integer> visiblePersonList) {
        this.visiblePersonList = visiblePersonList;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public List<Map<String, String>> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<Map<String, String>> fileNames) {
        this.fileNames = fileNames;
    }
}
