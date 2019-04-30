package com.wisewin.backend.entity.param;

import java.util.Date;

/**
 * 字典实体类，取到数据可以先保存在这个里
 */
public class DictionariesParam {
    private Integer id; //字典id
    private String key; //类型名字
    private String value; //类型
    private Integer dnId; //连接字典类型表
    private String dnName; //创建人
    private Integer updateUserId; //修改用户id
    private Date dnReleasetime; //最后修改时间时间
    private Double rank; //排序


    public DictionariesParam(){}

    public DictionariesParam(String key, String value, Integer dnId, String dnName, Integer updateUserId, Double rank) {
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

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public void setDnReleasetime(Date dnReleasetime) {
        this.dnReleasetime = dnReleasetime;
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

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public Date getDnReleasetime() {
        return dnReleasetime;
    }

    public Double getRank() {
        return rank;
    }
}
