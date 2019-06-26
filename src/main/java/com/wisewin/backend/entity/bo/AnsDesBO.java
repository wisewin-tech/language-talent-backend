package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

public class AnsDesBO<T> extends BaseModel{

    private T ans;//答案
    private String des;//解析

    public T getAns() {
        return ans;
    }

    public void setAns(T ans) {
        this.ans = ans;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
