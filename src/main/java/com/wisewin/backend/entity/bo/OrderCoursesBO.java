package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class OrderCoursesBO extends BaseModel {
    private Integer id; //订单课程关联表
    private Integer userId; //用户id
    private Integer orderId; //订单id
    private Integer coursesId; //课程id
    private String coursesName; //课程名称
    private Date courseValidityPeriod;//有效期
    private String standby; //备用
    private Integer createId; //创建人id
    private Integer updateId; //修改人id
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public Date getCourseValidityPeriod() {
        return courseValidityPeriod;
    }

    public void setCourseValidityPeriod(Date courseValidityPeriod) {
        this.courseValidityPeriod = courseValidityPeriod;
    }

    @Override
    public String toString() {
        return "OrderCoursesDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", coursesId=" + coursesId +
                ", coursesName='" + coursesName + '\'' +
                ", standby='" + standby + '\'' +
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getStandby() {
        return standby;
    }

    public void setStandby(String standby) {
        this.standby = standby;
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
