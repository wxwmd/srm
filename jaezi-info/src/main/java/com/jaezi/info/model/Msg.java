package com.jaezi.info.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 公告实体类
 */

public class Msg extends BaseModel {

    private Integer id;              //公告ID
    private String title;           //标题
    private Integer type;           //类型 1:企业新闻 2:企业公告 3:行业动态
    private String content;         //公告内容
    private Integer status;         //状态 0：暂存 1：发布 2：已删除
    private Long createTime;        //发布时间
    private String accessoryCollection;    //附件集合信息
    private String createUser;      //创建人
    private Integer sort;           //排序
    /**
     * 可见性（0：部分供应商，1：全部供应商，2：企业）
     */
    private Integer visible;

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getAccessoryCollection() {
        return accessoryCollection;
    }

    public void setAccessoryCollection(String accessoryCollection) {
        this.accessoryCollection = accessoryCollection;
    }
}
