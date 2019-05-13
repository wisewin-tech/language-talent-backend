package com.wisewin.backend.entity.dto;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

public class GiftRecordDTO extends BaseModel {
    private Integer id; //礼品记录id
    private Integer giftid; //礼品卡iD
    private Integer  userid; //用户id
    private Date grReleasetime; //兑换时间
    private Integer exchangevalue; //兑换值
    private String statecase; //状态（兑换时间或获取礼品卡时间）
    private String annotation; //备注

    @Override
    public String toString() {
        return "GiftRecordBO{" +
                "id=" + id +
                ", giftid=" + giftid +
                ", userid=" + userid +
                ", grReleasetime=" + grReleasetime +
                ", exchangevalue=" + exchangevalue +
                ", statecase='" + statecase + '\'' +
                ", annotation='" + annotation + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGiftid() {
        return giftid;
    }

    public void setGiftid(Integer giftid) {
        this.giftid = giftid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getGrReleasetime() {
        return grReleasetime;
    }

    public void setGrReleasetime(Date grReleasetime) {
        this.grReleasetime = grReleasetime;
    }

    public Integer getExchangevalue() {
        return exchangevalue;
    }

    public void setExchangevalue(Integer exchangevalue) {
        this.exchangevalue = exchangevalue;
    }

    public String getStatecase() {
        return statecase;
    }

    public void setStatecase(String statecase) {
        this.statecase = statecase;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
