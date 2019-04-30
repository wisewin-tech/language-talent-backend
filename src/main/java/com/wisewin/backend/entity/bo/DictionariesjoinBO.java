package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * 字典类型和字典内容总合表
 */
public class DictionariesjoinBO extends BaseModel {

    private String value; //类型
    private String dnName; //创建人
    private Date dnReleasetime; //发布时间
    private Integer updateUserId; //修改用户id
    private Double rank; //排序
    private String keyName; //类型名字


    public  DictionariesjoinBO(){}

    public DictionariesjoinBO(String value, String dnName, Date dnReleasetime, Integer updateUserId, Double rank, String keyName) {
        this.value = value;
        this.dnName = dnName;
        this.dnReleasetime = dnReleasetime;
        this.updateUserId = updateUserId;
        this.rank = rank;
        this.keyName = keyName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDnName(String dnName) {
        this.dnName = dnName;
    }

    public void setDnReleasetime(Date dnReleasetime) {
        this.dnReleasetime = dnReleasetime;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValue() {
        return value;
    }

    public String getDnName() {
        return dnName;
    }

    public Date getDnReleasetime() {
        return dnReleasetime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public Double getRank() {
        return rank;
    }

    public String getKeyName() {
        return keyName;
    }
}
