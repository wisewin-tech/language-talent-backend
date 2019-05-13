package com.wisewin.backend.entity.bo;

import com.wisewin.backend.util.dates.DateUtil;

import java.util.Date;

public class GiftRecordResultBO {
    private Integer id; //礼品记录id
    private Integer giftId; //礼品卡iD
    private Integer  userId; //用户id
    private String  nickname; //用户名称
    private String  mobile; //用户手机号
    private String  title; //标题名称
    private Integer value; //兑换值
    private String grReleasetime; //兑换时间
    private String createTime; //创建时间

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

    public String getGrReleasetime() {
        return DateUtil.getStr(grReleasetime);
    }

    public void setGrReleasetime(String grReleasetime) {
        this.grReleasetime = DateUtil.getStr(grReleasetime);
    }

    public String getCreateTime() {
        return DateUtil.getStr(createTime);
    }

    public void setCreateTime(String createTime) {
        this.createTime = DateUtil.getStr(createTime);
    }
}
