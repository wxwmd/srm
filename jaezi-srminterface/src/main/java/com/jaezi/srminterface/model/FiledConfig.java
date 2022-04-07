package com.jaezi.srminterface.model;

import com.jaezi.common.base.BaseModel;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 数据列配置实体类
 */
public class FiledConfig extends BaseModel {
    private Integer id;
    /**
     * 列名称
     */
    private String filedName;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 默认值
     */
    private String defaultValues;
    /**
     * 是否为空
     */
    private Integer isNull = 0;
    /**
     * 是否主键
     */
    private Integer isPk = 0;
    /**
     * 注释
     */
    private String comments;
    /**
     * 值内容
     */
    private String value;
    /**
     * 数据表ID
     */
    private Integer tableId;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(String defaultValues) {
        this.defaultValues = defaultValues;
    }

    public Integer getIsNull() {
        return isNull;
    }

    public void setIsNull(Integer isNull) {
        this.isNull = isNull;
    }

    public Integer getIsPk() {
        return isPk;
    }

    public void setIsPk(Integer isPk) {
        this.isPk = isPk;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
