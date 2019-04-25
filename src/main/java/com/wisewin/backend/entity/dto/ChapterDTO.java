package com.wisewin.backend.entity.dto;

import java.util.Date;

public class ChapterDTO {
    private Integer id; //课时表
    private String chapterName; //课时名称
    private Integer levelId; //级别id
    private String videoPath; //链接地址
    private String duration; //时长
    private String chapterIntro; //简介
    private String manuscript; //文稿
    private String freeOrNot; //是否免费观看
    private Integer serialNumber; //序号
    private Integer createUserId; //添加人id
    private Date createTime; //创建时间
    private Integer updateUserId; //添加人姓名
    private Date updateTime; //修改时间

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
}
