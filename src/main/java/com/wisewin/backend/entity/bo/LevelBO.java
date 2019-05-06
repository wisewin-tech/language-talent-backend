package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;
import java.util.Date;

public class LevelBO extends BaseModel {
    private Integer id; //级别表
    private String courseName;//课程名字
    private String levelName;//级别名字
    private Integer courseId;//课程id
    private String  status;//状态 putaway/soldout
    private String levelIntro;//简介
    private Double serialNumber;//序号
    private String medalImageUrl;//勋章图片地址
    private String medalName;//勋章名称
    private Integer createUserId;//添加人id
    private Integer updateUserId;//修改人id
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevelIntro() {
        return levelIntro;
    }

    public void setLevelIntro(String levelIntro) {
        this.levelIntro = levelIntro;
    }

    public Double getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Double serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMedalImageUrl() {
        return medalImageUrl;
    }

    public void setMedalImageUrl(String medalImageUrl) {
        this.medalImageUrl = medalImageUrl;
    }

    public String getMedalName() {
        return medalName;
    }

    public void setMedalName(String medalName) {
        this.medalName = medalName;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
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
