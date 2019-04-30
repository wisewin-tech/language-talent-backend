package com.wisewin.backend.entity.param;

/**
 * 学习语言目的
 */
public class LanguagepurposeParam {

    private Integer id; //学习语言目的id
    private Integer purposeId; //目的 id
    private Integer userId; //用户iD


    public LanguagepurposeParam(Integer purposeId, Integer userId) {
        this.purposeId = purposeId;
        this.userId = userId;
    }

    public  LanguagepurposeParam(){}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPurposeId(Integer purposeId) {
        this.purposeId = purposeId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
