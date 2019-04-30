package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class LikerelationBO extends BaseModel {

    private Integer lkId; //喜欢关系id
    private Integer userId; //用户id
    private Integer dcId; //发现id
    private Date lkReleasetime; //创建时间

    public LikerelationBO(){}

    public LikerelationBO(Integer userId, Integer dcId, Date lkReleasetime) {
        this.userId = userId;
        this.dcId = dcId;
        this.lkReleasetime = lkReleasetime;
    }

    public void setLkId(Integer lkId) {
        this.lkId = lkId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setDcId(Integer dcId) {
        this.dcId = dcId;
    }

    public void setLkReleasetime(Date lkReleasetime) {
        this.lkReleasetime = lkReleasetime;
    }

    public Integer getLkId() {
        return lkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getDcId() {
        return dcId;
    }

    public Date getLkReleasetime() {
        return lkReleasetime;
    }
}
