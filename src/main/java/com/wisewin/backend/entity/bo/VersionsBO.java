package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class VersionsBO extends BaseModel{

    private Integer id; //版本id
    private Integer versionsnum; //发版次数
    private String model; //版本号
    private String content; //内容
    private Integer adminId; //后台管理员id
    private Date createTime; //创建时间
    private Integer updateadminId; //后台管理员修改id
    private Date updateTime; //修改时间
    private String compatibility; //兼容版本


    public VersionsBO(){}

    public VersionsBO(Integer id, Integer versionsnum, String model, String content, Integer updateadminId, String compatibility) {
        this.id = id;
        this.versionsnum = versionsnum;
        this.model = model;
        this.content = content;
        this.updateadminId = updateadminId;
        this.compatibility = compatibility;
    }

    public VersionsBO(Integer versionsnum, String model, String content, Integer adminId, Integer updateadminId, String compatibility) {
        this.versionsnum = versionsnum;
        this.model = model;
        this.content = content;
        this.adminId = adminId;
        this.updateadminId = updateadminId;
        this.compatibility = compatibility;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionsnum() {
        return versionsnum;
    }

    public void setVersionsnum(Integer versionsnum) {
        this.versionsnum = versionsnum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Integer getUpdateadminId() {
        return updateadminId;
    }

    public void setUpdateadminId(Integer updateadminId) {
        this.updateadminId = updateadminId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }
}
