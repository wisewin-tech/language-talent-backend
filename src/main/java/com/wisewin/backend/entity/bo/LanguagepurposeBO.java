package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

/**
 * 学习语言目的
 */
public class LanguagepurposeBO extends BaseModel{

    private Integer id; //学习语言目的id
    private Integer purposeId; //目的 id
    private Integer userId; //用户iD
    private Date lpUpdatetime;  //最后修改时间时间

    public  LanguagepurposeBO(){}
    public LanguagepurposeBO(Integer purposeId, Integer userId) {
        this.purposeId = purposeId;
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPurposeId(Integer purposeId) {
        this.purposeId = purposeId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLpUpdatetime(Date lpUpdatetime) {
        this.lpUpdatetime = lpUpdatetime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPurposeId() {
        return purposeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Date getLpUpdatetime() {
        return lpUpdatetime;
    }
}
