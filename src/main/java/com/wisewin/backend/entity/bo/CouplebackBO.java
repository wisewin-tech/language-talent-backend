package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class CouplebackBO extends BaseModel {
    private Integer id; //意见反馈id
    private Integer userId; //用户id
    private String content; //反馈内容
    private String contactpattern; //用户联系方式
    private String pictureUrl; //图片url
    private Date cbReleasetime; //创建时间
    private Date cbUpdatetime; //修改时间
    private String disposeresUlt; //处理结果
    private String disposeperson; //处理人


    public CouplebackBO(Integer userId, String content, String contactpattern, String pictureUrl) {
        this.userId = userId;
        this.content = content;
        this.contactpattern = contactpattern;
        this.pictureUrl = pictureUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContactpattern() {
        return contactpattern;
    }

    public void setContactpattern(String contactpattern) {
        this.contactpattern = contactpattern;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Date getCbReleasetime() {
        return cbReleasetime;
    }

    public void setCbReleasetime(Date cbReleasetime) {
        this.cbReleasetime = cbReleasetime;
    }

    public Date getCbUpdatetime() {
        return cbUpdatetime;
    }

    public void setCbUpdatetime(Date cbUpdatetime) {
        this.cbUpdatetime = cbUpdatetime;
    }

    public String getDisposeresUlt() {
        return disposeresUlt;
    }

    public void setDisposeresUlt(String disposeresUlt) {
        this.disposeresUlt = disposeresUlt;
    }

    public String getDisposeperson() {
        return disposeperson;
    }

    public void setDisposeperson(String disposeperson) {
        this.disposeperson = disposeperson;
    }
}
