package com.wisewin.backend.entity.dto;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

public class GoalDTO extends BaseModel{

    private Integer Id; //目的id
    private String ppPurpose; //目的
    private Integer adminId; //创建人
    private Date ppReleasetime; //创建时间
    private Date ppUpdatetime; //修改时间
    private double rank;//排序


    public GoalDTO(){}



    public void setId(Integer id) {
        Id = id;
    }

    public void setPpPurpose(String ppPurpose) {
        this.ppPurpose = ppPurpose;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public void setPpReleasetime(Date ppReleasetime) {
        this.ppReleasetime = ppReleasetime;
    }

    public void setPpUpdatetime(Date ppUpdatetime) {
        this.ppUpdatetime = ppUpdatetime;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return Id;
    }

    public String getPpPurpose() {
        return ppPurpose;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public Date getPpReleasetime() {
        return ppReleasetime;
    }

    public Date getPpUpdatetime() {
        return ppUpdatetime;
    }

    public double getRank() {
        return rank;
    }
}
