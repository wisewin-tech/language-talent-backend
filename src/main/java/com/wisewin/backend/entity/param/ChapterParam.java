package com.wisewin.backend.entity.param;

public class ChapterParam {
    private String chapterName;// 课时名字
    private String status ;// 状态  putaway/soldout
    private String  freeOrNot;// 是否免费观看  yes/no
    private Integer pageNo;//当前页
    private Integer pageSize;//页面容量

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
}
