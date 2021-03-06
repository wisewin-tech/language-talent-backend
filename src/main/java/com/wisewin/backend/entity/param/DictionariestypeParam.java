package com.wisewin.backend.entity.param;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * 字典类型表
 */
public class DictionariestypeParam extends BaseModel {

    private Integer id; //字典类型表id
    private String keyName; //类型名字
    private Double rank; //排序
    private Integer updateNameId; //最后修改人id
    private Date updateTime; //最后修改id
    private String valueName; //类型内容


    public DictionariestypeParam(){}


    public DictionariestypeParam(Integer id, String keyName, Double rank, Integer updateNameId) {
        this.id = id;
        this.keyName = keyName;
        this.rank = rank;
        this.updateNameId = updateNameId;
    }

    public DictionariestypeParam(String keyName, Double rank, Integer updateNameId) {
        this.keyName = keyName;
        this.rank = rank;
        this.updateNameId = updateNameId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }


    public void setRank(Double rank) {
        this.rank = rank;
    }

    public void setUpdateNameId(Integer updateNameId) {
        this.updateNameId = updateNameId;
    }

    public Integer getId() {
        return id;
    }

    public String getKeyName() {
        return keyName;
    }


    public Double getRank() {
        return rank;
    }

    public Integer getUpdateNameId() {
        return updateNameId;
    }

    @Override
    public String toString() {
        return "DictionariestypeParam{" +
                "id=" + id +
                ", keyName='" + keyName + '\'' +
                ", rank=" + rank +
                ", updateNameId=" + updateNameId +
                ", updateTime=" + updateTime +
                ", valueName='" + valueName + '\'' +
                '}';
    }
}
