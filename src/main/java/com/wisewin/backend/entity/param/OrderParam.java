package com.wisewin.backend.entity.param;

import com.wisewin.backend.common.base.BaseModel;

public class OrderParam extends BaseModel {
    private Integer pageNo;
    private Integer pageSize;
    private String orderNumber;//订单号
    private String mobile;//手机号
    private String afterTime;//这个时间之后
    private String beforeTime;//这个时间之前

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }



    public String getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(String afterTime) {
        this.afterTime = afterTime;
    }

    public String getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(String beforeTime) {
        this.beforeTime = beforeTime;
    }
}
