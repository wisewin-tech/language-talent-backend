package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class SystemoperationBO  extends BaseModel{
    private Integer id; //系统操作id
    private Integer adminId; //后台id
    private String content; //内容
    private String contenttype; //内容类型
    private String operationtype; //操作类型(增删改查)
    private Date soReleasetime; //操作时间
    private Integer  page; //页数
    private Integer strip; //条数

    public SystemoperationBO(){}

    public SystemoperationBO(Integer id, Integer adminId, String content, String contenttype, String operationtype, Date soReleasetime) {
        this.id = id;
        this.adminId = adminId;
        this.content = content;
        this.contenttype = contenttype;
        this.operationtype = operationtype;
        this.soReleasetime = soReleasetime;
    }

    public SystemoperationBO(Integer adminId, String content, String contenttype, String operationtype) {
        this.adminId = adminId;
        this.content = content;
        this.contenttype = contenttype;
        this.operationtype = operationtype;
    }

    public SystemoperationBO(Integer id, Integer adminId, String content, String contenttype, String operationtype, Date soReleasetime, Integer page, Integer strip) {
        this.id = id;
        this.adminId = adminId;
        this.content = content;
        this.contenttype = contenttype;
        this.operationtype = operationtype;
        this.soReleasetime = soReleasetime;
        this.page = page;
        this.strip = strip;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStrip() {
        return strip;
    }

    public void setStrip(Integer strip) {
        this.strip = strip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    public Date getSoReleasetime() {
        return soReleasetime;
    }

    public void setSoReleasetime(Date soReleasetime) {
        this.soReleasetime = soReleasetime;
    }
}
