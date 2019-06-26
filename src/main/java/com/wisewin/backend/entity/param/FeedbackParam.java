package com.wisewin.backend.entity.param;

import java.util.Date;

public class FeedbackParam {
    private Integer id;//id
    private String status;//状态
    private Date begin;//开始时间
    private  Date end;//结束时间
    private Integer adminid;//管理员id
    private Date updatetime;//修改时间
    private Integer pageNo;//起始页
    private Integer pageSize;//每页条数
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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

    @Override
    public String toString() {
        return "FeedbackParam{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", adminid=" + adminid +
                ", updatetime=" + updatetime +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
