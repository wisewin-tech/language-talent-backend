package com.wisewin.backend.entity.bo;



import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * 字典表
 */
public class DictionariesBO extends BaseModel {

    private Integer id; //字典id
    private String key; //类型名字
    private String value; //类型
    private Integer dnId; //连接字典类型表
    private String dnName; //创建人
    private Date dnReleasetime; //发布时间
    private Integer updateUserId; //修改用户id
    private Double rank; //排序

    public DictionariesBO() {
    }

    public DictionariesBO(Integer id, String key, String value, Integer dnId, String dnName, Date dnReleasetime, Integer updateUserId, Double rank) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.dnId = dnId;
        this.dnName = dnName;
        this.dnReleasetime = dnReleasetime;
        this.updateUserId = updateUserId;
        this.rank = rank;
    }

    public DictionariesBO(String key, String value, Integer dnId, String dnName, Integer updateUserId, Double rank) {
        this.key = key;
        this.value = value;
        this.dnId = dnId;
        this.dnName = dnName;
        this.updateUserId = updateUserId;
        this.rank = rank;
    }

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

    public Double getRank() {
        return rank;
    }
}
