package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

public class AboutUsBO extends BaseModel{
    private Integer id; //关于我们
    private String imageUrl; //图片路径
    private String content; //内容
    private Integer adminId; //管理员id
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    @Override
    public String toString() {
        return "AboutUsBO{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", content='" + content + '\'' +
                ", adminId=" + adminId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
