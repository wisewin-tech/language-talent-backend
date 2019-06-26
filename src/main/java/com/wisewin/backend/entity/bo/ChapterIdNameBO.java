package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

public class ChapterIdNameBO extends BaseModel {
    private Integer id;//课时id
    private String chapterName;//课时名称

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
}
