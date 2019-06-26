package com.wisewin.backend.entity.param;


import com.wisewin.backend.entity.bo.common.base.BaseModel;

public class MenuParam extends BaseModel {
    private Integer id;
    private String menuName; // 菜单名称
    private String status; // 状态
    private Integer pid; // 父id
    private String url; // 路径
    private String index;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MenuParam{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", status='" + status + '\'' +
                ", pid=" + pid +
                ", url='" + url + '\'' +
                ", index='" + index + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
