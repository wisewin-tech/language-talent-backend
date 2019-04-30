package com.wisewin.backend.entity.dto;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * 字典类型表
 */
public class DictionariestypeDTO extends BaseModel {

    private Integer id; //字典类型表id
    private String keyName; //类型名字
    private Double rank; //排序
    private Integer updateNameId; //最后修改人id
    private Date updateTime; //最后修改时间



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
