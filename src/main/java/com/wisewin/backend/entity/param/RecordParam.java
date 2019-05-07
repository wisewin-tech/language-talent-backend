package com.wisewin.backend.entity.param;

import com.wisewin.backend.common.base.BaseModel;

public class RecordParam extends BaseModel {
    private Integer id;
    private Integer pageNo;
    private Integer pageSize;
    private String source;
    private String afterTime;
    private String beforeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(String afterTime) {
        this.afterTime = afterTime;
    }

    public String getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(String beforeTime) {
        this.beforeTime = beforeTime;
    }
}
