package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

public class AnsDesBO extends BaseModel{

    private Integer ans;//答案
    private String des;//解析

    public Integer getAns() {
        return ans;
    }

    public void setAns(Integer ans) {
        this.ans = ans;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
