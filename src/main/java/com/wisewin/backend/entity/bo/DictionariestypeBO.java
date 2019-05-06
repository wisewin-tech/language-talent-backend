package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * 字典类型表
 */
public class DictionariestypeBO extends BaseModel {

    private Integer id; //字典类型表id
    private String keyName; //类型名字
    private Double rank; //排序
    private Integer updateNameId; //最后修改人id
    private Date updateTime; //最后修改时间
    private String valueName; //类型内容

    public DictionariestypeBO(String keyName, Double rank, Integer updateNameId, String valueName) {
        this.keyName = keyName;
        this.rank = rank;
        this.updateNameId = updateNameId;
        this.valueName = valueName;
    }

    public DictionariestypeBO(){}

    public DictionariestypeBO(Integer id, String keyName, String valueName) {
        this.id = id;
        this.keyName = keyName;
        this.valueName = valueName;
    }

    public DictionariestypeBO(Integer id, String keyName, Double rank, Integer updateNameId, Date updateTime, String valueName) {
        this.id = id;
        this.keyName = keyName;
        this.rank = rank;
        this.updateNameId = updateNameId;
        this.updateTime = updateTime;
        this.valueName = valueName;
    }

    public DictionariestypeBO(String keyName , Double rank, Integer updateNameId) {
        this.keyName = keyName;
        this.rank = rank;
        this.updateNameId = updateNameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Integer getUpdateNameId() {
        return updateNameId;
    }

    public void setUpdateNameId(Integer updateNameId) {
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
}
