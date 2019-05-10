package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class CouplebackBO extends BaseModel {

    private Integer id; //意见反馈id
    private Integer userid; //用户id
    private String content; //反馈内容
    private String contactpattern; //用户联系方式
    private String pattern; //用户联系
    private String pictureurl; //图片url
    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    private String disposeresult; //处理结果
    private String disposeperson; //处理人


    public CouplebackBO(String content, String contactpattern, String pattern, String pictureurl, Date updateTime, String disposeresult, String disposeperson) {
        this.content = content;
        this.contactpattern = contactpattern;
        this.pattern = pattern;
        this.pictureurl = pictureurl;
        this.updateTime = updateTime;
        this.disposeresult = disposeresult;
        this.disposeperson = disposeperson;
    }

    public  CouplebackBO(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDisposeresult() {
        return disposeresult;
    }

    public void setDisposeresult(String disposeresult) {
        this.disposeresult = disposeresult;
    }

    public String getDisposeperson() {
        return disposeperson;
    }

    public void setDisposeperson(String disposeperson) {
        this.disposeperson = disposeperson;
    }
}
