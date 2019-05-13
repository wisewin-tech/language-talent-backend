package com.wisewin.backend.entity.param;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

public class GiftRecordParam extends BaseModel {
    private Integer id; //礼品记录id
    private Integer giftId; //礼品卡iD
    private Integer  userId; //用户id
    private String  nickname; //用户名称
    private String  mobile; //用户手机号
    private String  title; //标题名称
    private Integer value; //兑换值
    private Date grReleasetime; //兑换时间
    private Date createTime; //创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Date getGrReleasetime() {
        return grReleasetime;
    }

    public void setGrReleasetime(Date grReleasetime) {
        this.grReleasetime = grReleasetime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
