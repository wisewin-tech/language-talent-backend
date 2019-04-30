package com.wisewin.backend.entity.dto;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * 字典类型和字典内容总合表
 */
public class DictionariesjoinDTO extends BaseModel {

    private String key; //类型名字
    private String value; //类型
    private Integer dnId; //连接字典类型表
    private String dnName; //创建人
    private Date dnReleasetime; //发布时间
    private Integer updateUserId; //修改用户id
    private Double rank; //排序
    private String keyName; //类型名字
    private Integer keyId; //外键id


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

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
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

    public String getKeyName() {
        return keyName;
    }

    public Integer getKeyId() {
        return keyId;
    }
}
