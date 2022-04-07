package com.jaezi.info.dto;

import com.jaezi.info.model.Msg;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/9/1 15:55
 * @description
 */
public class MsgDto extends Msg {

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
