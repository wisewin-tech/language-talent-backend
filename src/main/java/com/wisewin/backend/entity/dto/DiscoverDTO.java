package com.wisewin.backend.entity.dto;

import com.wisewin.backend.entity.bo.DiscoverBO;

import java.util.List;

/**
 * Created by 王彬 on 2019/5/6.
 */
public class DiscoverDTO {
   private  List<DiscoverBO> list;
   private Integer count;
/*    private String  type;
    private String dcReleasetime;
    private String title;*/

    public List<DiscoverBO> getList() {
        return list;
    }

    public void setList(List<DiscoverBO> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
