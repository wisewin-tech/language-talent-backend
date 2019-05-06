package com.wisewin.backend.entity.dto;

import com.wisewin.backend.entity.bo.DiscoverBO;

import java.util.List;

/**
 * Created by 王彬 on 2019/5/6.
 */
public class DiscoverDTO {
   private  List<DiscoverBO> list;
    private String  type;
    private String dcReleasetime;
    private String title;

    public List<DiscoverBO> getList() {
        return list;
    }

    public void setList(List<DiscoverBO> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDcReleasetime() {
        return dcReleasetime;
    }

    public void setDcReleasetime(String dcReleasetime) {
        this.dcReleasetime = dcReleasetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
