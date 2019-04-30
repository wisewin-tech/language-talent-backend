package com.wisewin.backend.entity.dto;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

/**
 * 学习语言目的
 */
public class LanguagepurposeDTO extends BaseModel{

    private Integer id; //学习语言目的id
    private Integer purposeId; //目的 id
    private Integer userId; //用户iD
    private Date lpUpdatetime;  //最后修改时间时间

    public LanguagepurposeDTO(){}
    public LanguagepurposeDTO(Integer purposeId, Integer userId, Date lpUpdatetime) {
        this.purposeId = purposeId;
        this.userId = userId;
        this.lpUpdatetime = lpUpdatetime;
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
