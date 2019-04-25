package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class UserStdayBO extends BaseModel {
    private Integer id; //学习详情表
    private Integer userId; //用户id
    private Date studyDate; //学习日期
    private Integer studyTime; //学习时长
    private Integer createId; //创建人id
    private Integer updateId; //修改人id
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    @Override
    public String toString() {
        return "UserStdayDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", studyDate=" + studyDate +
                ", studyTime=" + studyTime +
                ", createId=" + createId +
                ", updateId=" + updateId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
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

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public Integer getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Integer studyTime) {
        this.studyTime = studyTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
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
}
