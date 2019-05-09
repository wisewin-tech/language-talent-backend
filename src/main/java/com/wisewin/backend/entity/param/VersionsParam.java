package com.wisewin.backend.entity.param;

import java.util.Date;

public class VersionsParam {

    private Integer id; //版本id
    private Integer versionsnum; //发版次数
    private String model; //版本号
    private String content; //内容
    private Integer adminId; //后台管理员id
    private String createTime; //创建时间
    private Integer updateadminId; //后台管理员修改id
    private String updateTime; //修改时间
    private String compatibility; //兼容版本
    private Integer pageNo; //页数
    private Integer pageSize; //条数


    public Integer getId() {
        return id;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateadminId() {
        return updateadminId;
    }

    public void setUpdateadminId(Integer updateadminId) {
        this.updateadminId = updateadminId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }
}
