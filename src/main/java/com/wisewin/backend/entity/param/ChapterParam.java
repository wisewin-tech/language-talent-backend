package com.wisewin.backend.entity.param;

public class ChapterParam {
    private String chapterName;// 课时名字
    private String status ;// 状态  putaway/soldout
    private String  freeOrNot;// 是否免费观看  yes/no
    private Integer pageNo;//当前页
    private Integer pageSize;//页面容量
    private Integer levelId; //级别id
    private Integer languageId;//语言id
    private Integer courseId;//课程id

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFreeOrNot() {
        return freeOrNot;
    }

    public void setFreeOrNot(String freeOrNot) {
        this.freeOrNot = freeOrNot;
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
        return "ChapterParam{" +
                "chapterName='" + chapterName + '\'' +
                ", status='" + status + '\'' +
                ", freeOrNot='" + freeOrNot + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", levelId=" + levelId +
                ", languageId=" + languageId +
                ", courseId=" + courseId +
                '}';
    }
}
