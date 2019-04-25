package com.wisewin.backend.entity.dto;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;
import java.util.List;

public class RoleDTO extends BaseModel {
    private Integer id; // 角色

    private String roleName; //角色名称

    private Date createTime; //创建时间按

    private Date updateTime; // 修改时间

    private List<Integer> menuIds; // 角色对应的所有权限的ids

    private List<String> menuNames;// 角色对应的所有的权限名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

    public List<String> getMenuNames() {
        return menuNames;
    }

    public void setMenuNames(List<String> menuNames) {
        this.menuNames = menuNames;
    }
}
