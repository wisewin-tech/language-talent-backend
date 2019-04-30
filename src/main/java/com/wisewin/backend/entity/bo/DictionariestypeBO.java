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

    public DictionariestypeBO(){}

    public DictionariestypeBO(String keyName) {
        this.keyName = keyName;

    }

    public DictionariestypeBO(Integer id, String keyName, Double rank, Integer updateNameId, Date updateTime) {
        this.id = id;
        this.keyName = keyName;
        this.rank = rank;
        this.updateNameId = updateNameId;
        this.updateTime = updateTime;
    }

    public DictionariestypeBO(String keyName , Double rank, Integer updateNameId) {
        this.keyName = keyName;
        this.rank = rank;
        this.updateNameId = updateNameId;
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

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }
}
