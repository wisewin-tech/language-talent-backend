package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

/**
 * 专题
 * */
public class SpecialBO extends BaseModel {

    private Integer id;
    private Integer specialId;//专题分类id
    private String describe;//专题描述
    private Date releaseDate;//专题上传日期
    private Integer traffic;//专题访问量
    private String video_url;//专题视频地址
    private String status;//是否展示（yes展示，no不展示）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Integer specialId) {
        this.specialId = specialId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
