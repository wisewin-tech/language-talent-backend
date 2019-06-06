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
    private String outer; //连接字典类型表valuename

    public DictionariesBO() {
    }

    public DictionariesBO(String value) {
        this.value = value;
    }

    public DictionariesBO(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public DictionariesBO(String key, String value, String dnName, Integer updateUserId, Double rank, String outer) {
        this.key = key;
        this.value = value;
        this.dnName = dnName;
        this.updateUserId = updateUserId;
        this.rank = rank;
        this.outer = outer;
    }

    /**
     *添加
     *  String key; //类型名字
     *  String value; //类型
     *  Integer outerId; //连接字典类型表
     *  String dnName; //创建人
     *  Integer updateUserId; //修改用户id
     *  Double rank; //排序
     */


    public DictionariesBO(Integer id, String key, String value, String dnName, Date dnReleasetime, String outer, Integer updateUserId, Double rank) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.dnName = dnName;
        this.dnReleasetime = dnReleasetime;
        this.outer = outer;
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


    public String getOuter() {
        return outer;
    }

    public void setOuter(String outer) {
        this.outer = outer;
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

    @Override
    public String toString() {
        return "DictionariesBO{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", dnId=" + dnId +
                ", dnName='" + dnName + '\'' +
                ", dnReleasetime=" + dnReleasetime +
                ", updateUserId=" + updateUserId +
                ", rank=" + rank +
                ", outer='" + outer + '\'' +
                '}';
    }
}
