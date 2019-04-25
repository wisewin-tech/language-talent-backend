package com.wisewin.backend.entity.dto;

import java.util.Date;

public class StudyCoursesDTO {
    private Integer id; //学习课程表(正在学习的课程)
    private Integer userId; //用户id
    private Integer coursesId; //课程id
    private String coursesName; //课程名称
    private Integer createId; //创建人id
    private Integer updateId; //修改人id
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    @Override
    public String toString() {
        return "StudyCoursesDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", coursesId=" + coursesId +
                ", coursesName='" + coursesName + '\'' +
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

    public Integer getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Integer coursesId) {
        this.coursesId = coursesId;
    }

    public String getCoursesName() {
        return coursesName;
    }

    public void setCoursesName(String coursesName) {
        this.coursesName = coursesName;
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
