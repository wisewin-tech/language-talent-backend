package com.wisewin.backend.entity.param;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class CouplebackParam  {

    private Integer id; //意见反馈id
    private Integer userid; //用户id
    private String content; //反馈内容
    private String contactpattern; //用户联系方式
    private String pattern; //用户联系
    private String pictureurl; //图片url
    private String createTime; //创建时间
    private String updateTime; //修改时间
    private String disposeresult; //处理结果
    private String disposeperson; //处理人

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    @Override
    public String toString() {
        return "CouplebackParam{" +
                "id=" + id +
                ", userid=" + userid +
                ", content='" + content + '\'' +
                ", contactpattern='" + contactpattern + '\'' +
                ", pattern='" + pattern + '\'' +
                ", pictureurl='" + pictureurl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", disposeresult='" + disposeresult + '\'' +
                ", disposeperson='" + disposeperson + '\'' +
                '}';
    }
}
