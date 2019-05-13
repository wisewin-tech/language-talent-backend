package com.wisewin.backend.entity.param;


import com.wisewin.backend.entity.bo.common.base.BaseModel;
import com.wisewin.backend.util.dates.DateUtil;

import java.util.Date;

public class GiftParam extends BaseModel{
    private Integer id; //礼品卡id
    private String title; //标题名字
    private Integer value; //兑换值
    private String cardnumber; //卡号
    private String scope; //范围
    private String exchangeyard; //兑换码
    private String starttime; //起始期
    private String finishtime; //结束效期
    private String cause; //不可用原因
    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    private String status; //状态(已用和未使用)英文来表示


    @Override
    public String toString() {
        return "GiftBO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", value=" + value +
                ", cardnumber='" + cardnumber + '\'' +
                ", scope='" + scope + '\'' +
                ", exchangeyard='" + exchangeyard + '\'' +
                ", starttime=" + starttime +
                ", finishtime=" + finishtime +
                ", cause='" + cause + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                '}';
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getExchangeyard() {
        return exchangeyard;
    }

    public void setExchangeyard(String exchangeyard) {
        this.exchangeyard = exchangeyard;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
