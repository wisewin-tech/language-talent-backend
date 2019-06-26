package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

public class ChapterIdBO extends BaseModel {
    private Integer languageId;//语言id
    private Integer courseId;//课程id
    private Integer levelId;//级别id
    private Integer chapterId;//课时id

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }
}
