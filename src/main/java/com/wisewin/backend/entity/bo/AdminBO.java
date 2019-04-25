package com.wisewin.backend.entity.bo;

import java.util.Date;
import java.util.List;

/**
 * Created by yxw on 2018/11/5.
 */
public class AdminBO {
    private Integer id;// 管理员用户

    private String phoneNumber; // 手机号

    private String name; // 名称

    private String password; // 密码

    private String status; // 状态

    private String gender; // 性别

    private int roleId; // 角色id

    private Date createTime; // 创建时间

    private Date updateTime; // 修改时间

    private String email;

    private List<RoleBO> roleBO;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleBO> getRoleBO() {
        return roleBO;
    }

    public void setRoleBO(List<RoleBO> roleBO) {
        this.roleBO = roleBO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
}
