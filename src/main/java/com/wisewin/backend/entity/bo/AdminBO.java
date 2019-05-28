package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;
import java.util.Set;

/**
 * Created by hexiaowen on 2018/11/5.
 */
public class AdminBO extends BaseModel {

    private Integer id;// 管理员用户

    private String phoneNumber; // 手机号

    private String name; // 名称

    private String password; // 密码

    private String status; // 状态

    private String gender; // 性别

    private int roleId; // 角色id

    private Date createTime; // 创建时间

    private Date updateTime; // 修改时间

    private String email;  //邮箱

    private  String roleName; //角色

    private Set<String> url;//所拥有的接口

    public Set<String> getUrl() {
        return url;
    }

    public void setUrl(Set<String> url) {
        this.url = url;
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
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {


        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "[id="+this.id+" ,name="+this.getName()+"]";
    }
}
