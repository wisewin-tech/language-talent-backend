package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

//用户邀请记录
public class InvitationRecordBO extends BaseModel {
    private Integer yqrId;//邀请人id
    private Integer byrId;//被邀请人id
    private String yqrNickName;//邀请人姓名
    private String byrNickName;//被邀请人姓名
    private String yqrMobile;//邀请人手机号
    private String byrMobile;//被邀请人手机号
    private Integer yqrSpecificAmount;//邀请人获得咖豆数
    private Integer byrSpecificAmount;//被邀请人获得咖豆数
    private String registeredChannels;//被邀请人注册渠道
    private Date createTime;//被邀请人注册时间

    public Integer getYqrId() {
        return yqrId;
    }

    public void setYqrId(Integer yqrId) {
        this.yqrId = yqrId;
    }

    public Integer getByrId() {
        return byrId;
    }

    public void setByrId(Integer byrId) {
        this.byrId = byrId;
    }

    public String getYqrNickName() {
        return yqrNickName;
    }

    public void setYqrNickName(String yqrNickName) {
        this.yqrNickName = yqrNickName;
    }

    public String getByrNickName() {
        return byrNickName;
    }

    public void setByrNickName(String byrNickName) {
        this.byrNickName = byrNickName;
    }

    public String getYqrMobile() {
        return yqrMobile;
    }

    public void setYqrMobile(String yqrMobile) {
        this.yqrMobile = yqrMobile;
    }

    public String getByrMobile() {
        return byrMobile;
    }

    public void setByrMobile(String byrMobile) {
        this.byrMobile = byrMobile;
    }

    public Integer getYqrSpecificAmount() {
        return yqrSpecificAmount;
    }

    public void setYqrSpecificAmount(Integer yqrSpecificAmount) {
        this.yqrSpecificAmount = yqrSpecificAmount;
    }

    public Integer getByrSpecificAmount() {
        return byrSpecificAmount;
    }

    public void setByrSpecificAmount(Integer byrSpecificAmount) {
        this.byrSpecificAmount = byrSpecificAmount;
    }

    public String getRegisteredChannels() {
        return registeredChannels;
    }

    public void setRegisteredChannels(String registeredChannels) {
        this.registeredChannels = registeredChannels;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
