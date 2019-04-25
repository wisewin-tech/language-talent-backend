package com.wisewin.backend.entity.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuBO {
    private Integer id; // 菜单

    private String menuName; // 菜单名称

    private String status; // 状态(菜单/按钮)

    private Integer pid; // 父id

    private Date createTime; //创建时间

    private Date updateTime; // 修改时间

    private String url;

    private String icon;

    private String index;

    private List<MenuBO> ch = new ArrayList<MenuBO>();

    private MenuBO menuBO;

    public List<MenuBO> getCh() {
        return ch;
    }

    public void setCh(List<MenuBO> ch) {
        this.ch = ch;
    }

    public MenuBO getMenuBO() {
        return menuBO;
    }

    public void setMenuBO(MenuBO menuBO) {
        this.menuBO = menuBO;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}
