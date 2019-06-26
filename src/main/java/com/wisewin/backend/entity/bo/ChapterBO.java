package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

public class ChapterBO extends BaseModel{
    private Integer id; //课时表
    private String chapterName; //课时名称
    private Integer levelId; //级别id
    private String content; //内容
    private String status; //状态(上下架)
    private String videoPath; //视频链接地址
    private String duration; //时长
    private String chapterIntro; //简介
    private String manuscript; //文稿
    private String freeOrNot; //是否免费观看
    private Integer serialNumber; //序号
    private String thumbnailUrl; //课时缩略图
    private Integer createUserId; //添加人id
    private Date createTime; //创建时间
    private Integer updateUserId; //添加人姓名
    private Date updateTime; //修改时间
    private Integer languageId;//语言id
    private Integer courseId;//课程id
    private String courseName;//课程名称
    private String languageName;//语言名称
    private String levelName;//级别名称

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Integer getLanguangId() {
        return languageId;
    }

    public void setLanguangId(Integer languangId) {
        this.languageId = languangId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getChapterIntro() {
        return chapterIntro;
    }

    public void setChapterIntro(String chapterIntro) {
        this.chapterIntro = chapterIntro;
    }

    public String getManuscript() {
        return manuscript;
    }

    public void setManuscript(String manuscript) {
        this.manuscript = manuscript;
    }

    public String getFreeOrNot() {
        return freeOrNot;
    }

    public void setFreeOrNot(String freeOrNot) {
        this.freeOrNot = freeOrNot;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ChapterBO{" +
                "id=" + id +
                ", chapterName='" + chapterName + '\'' +
                ", levelId=" + levelId +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", duration='" + duration + '\'' +
                ", chapterIntro='" + chapterIntro + '\'' +
                ", manuscript='" + manuscript + '\'' +
                ", freeOrNot='" + freeOrNot + '\'' +
                ", serialNumber=" + serialNumber +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", updateUserId=" + updateUserId +
                ", updateTime=" + updateTime +
                ", languageId=" + languageId +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", languageName='" + languageName + '\'' +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
