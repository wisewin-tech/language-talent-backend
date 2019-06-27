package com.wisewin.backend.entity.dto;

/**
 * @Author: Bin Wang
 * @date: Created in 13:05 2019/6/27
 */
public class LgDTO {
    private Integer id; //语言表
    private String languageName; //语言名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
