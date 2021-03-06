package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderBO extends BaseModel {
    private Integer id; //订单表
    private Integer userId; //用户id
    private String mobile;//用户手机号
    private String name;//用户名
    private String orderType;
    private String status;
    private Integer languageId; //语言id
    private Integer coursesId; //课程id
    private String languageName; //语言名称
    private String coursesName; //课程名称
    private BigDecimal price; //价格
    private String orderNumber; //订单号
    private String productName; //商品名称(拼接字段)
    private Date creationDate; //购买日期
    private Date courseValidityPeriod;//有效期
    private String standby; //备用
    private Integer createId; //创建人id
    private Integer updateId; //修改人id
    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    private String purchaseChannels;//购买渠道
    private List<OrderCoursesBO> orderCoursesBOList;//订单中的多个课程

    public String getPurchaseChannels() {
        return purchaseChannels;
    }

    public void setPurchaseChannels(String purchaseChannels) {
        this.purchaseChannels = purchaseChannels;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCourseValidityPeriod() {
        return courseValidityPeriod;
    }

    public void setCourseValidityPeriod(Date courseValidityPeriod) {
        this.courseValidityPeriod = courseValidityPeriod;
    }

    public List<OrderCoursesBO> getOrderCoursesBOList() {
        return orderCoursesBOList;
    }

    public void setOrderCoursesBOList(List<OrderCoursesBO> orderCoursesBOList) {
        this.orderCoursesBOList = orderCoursesBOList;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Integer coursesId) {
        this.coursesId = coursesId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getCoursesName() {
        return coursesName;
    }

    public void setCoursesName(String coursesName) {
        this.coursesName = coursesName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStandby() {
        return standby;
    }

    public void setStandby(String standby) {
        this.standby = standby;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
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

    @Override
    public String toString() {
        return "OrderBO{" +
                "id=" + id +
                ", userId=" + userId +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", orderType='" + orderType + '\'' +
                ", status='" + status + '\'' +
                ", languageId=" + languageId +
                ", coursesId=" + coursesId +
                ", languageName='" + languageName + '\'' +
                ", coursesName='" + coursesName + '\'' +
                ", price=" + price +
                ", orderNumber='" + orderNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", creationDate=" + creationDate +
                ", courseValidityPeriod=" + courseValidityPeriod +
                ", standby='" + standby + '\'' +
                ", createId=" + createId +
                ", updateId=" + updateId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", orderCoursesBOList=" + orderCoursesBOList +
                '}';
    }
}
