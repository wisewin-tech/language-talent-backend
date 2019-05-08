package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;
/**
 * 专题分类
 * */
public class SpecialClassBO extends BaseModel {
    private Integer id;//专题id
    private String title;//专题分类标题
    private String describe;//专分类题描述
    private String coverUrl;//专题分类封面图片地址
    private String status;//是否展示(yes为展示,no为不展示)

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


}
