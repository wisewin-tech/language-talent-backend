package com.wisewin.backend.entity.dto;

import java.util.Date;

public class LevelDTO {
    private Integer id; //级别表
    private Integer levelName; //级别名称
    private Integer courseId; //课程id
    private String levelIntro; //简介
    private Integer serialNumber; //序号
    private Integer createUserId; //添加人id
    private Integer updateUserId; //添加人姓名
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
