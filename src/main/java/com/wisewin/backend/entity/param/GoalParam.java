package com.wisewin.backend.entity.param;

import com.wisewin.backend.common.base.BaseModel;

/**
 * 目的
 */
public class GoalParam extends BaseModel {
    private  Integer id; //目的id
   private   String ppPurpose; //目的
   private   Integer adminId; //创建人
    private Double rank;//排序

    public GoalParam(){}

    public GoalParam(String ppPurpose, Integer adminId) {
        this.ppPurpose = ppPurpose;
        this.adminId = adminId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPpPurpose(String ppPurpose) {
        this.ppPurpose = ppPurpose;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public String getPpPurpose() {
        return ppPurpose;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public Double getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "GoalParam{" +
                "id=" + id +
                ", ppPurpose='" + ppPurpose + '\'' +
                ", adminId=" + adminId +
                ", rank=" + rank +
                '}';
    }
}
