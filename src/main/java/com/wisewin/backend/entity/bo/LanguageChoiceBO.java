package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

public class LanguageChoiceBO extends BaseModel {
    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
