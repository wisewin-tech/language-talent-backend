package com.wisewin.backend.entity.dto;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

/**
 * 字典表
 */
public class DictionariesDTO extends BaseModel {

    private Integer id; //字典id
    private String key; //类型名字
    private String value; //类型
    private Integer dnId; //连接字典类型表
    private String dnName; //创建人
    private Date dnReleasetime; //发布时间
    private Integer updateUserId; //修改用户id
    private String amend; //是否可以修改(yes/no)
    private Double rank; //排序

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDnId(Integer dnId) {
        this.dnId = dnId;
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

    public void setAmend(String amend) {
        this.amend = amend;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Integer getDnId() {
        return dnId;
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

    public String getAmend() {
        return amend;
    }

    public Double getRank() {
        return rank;
    }
}
