package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

/**
 * Created by 王彬 on 2019/5/5.
 */
public class DiscoverBO extends BaseModel {
    //发现id
    private Integer id;
    //发现标题
    private String title;
    //浏览人数
    private Integer browse;
    //创建人
    private String dcName;
    //发布时间
    private Date dcReleasetime;
    //最后修改人
    private String dcUpdatename;
    //最后修改时间
    private Date dcUpdatetime;
    //缩略图url
    private String thumbnail;
    //视频URL
    private String video;
    //内容
    private String content;
    //类型（课程，新闻，线下活动）
    private String type;
    //喜欢人数
    private Integer likenum;
    //要参与
    private Integer participation;
    //活动时间
    private Date activitytime;
    //活动地址
    private String activitysite;
    //联系电话
    private String phone;
    //在线购票
    private Double ticket;
    //优先级
    private Integer priority;
    //置顶（是与否）
    private String stick;
    //是否显示
    private String show;
    //跳转url（线下活动）
    private String skip;
    //展示方式【上下排序或左右排序】
    private String way;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public String getDcName() {
        return dcName;
    }

    public void setDcName(String dcName) {
        this.dcName = dcName;
    }

    public Date getDcReleasetime() {
        return dcReleasetime;
    }

    public void setDcReleasetime(Date dcReleasetime) {
        this.dcReleasetime = dcReleasetime;
    }

    public String getDcUpdatename() {
        return dcUpdatename;
    }

    public void setDcUpdatename(String dcUpdatename) {
        this.dcUpdatename = dcUpdatename;
    }

    public Date getDcUpdatetime() {
        return dcUpdatetime;
    }

    public void setDcUpdatetime(Date dcUpdatetime) {
        this.dcUpdatetime = dcUpdatetime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getParticipation() {
        return participation;
    }

    public void setParticipation(Integer participation) {
        this.participation = participation;
    }

    public Date getActivitytime() {
        return activitytime;
    }

    public void setActivitytime(Date activitytime) {
        this.activitytime = activitytime;
    }

    public String getActivitysite() {
        return activitysite;
    }

    public void setActivitysite(String activitysite) {
        this.activitysite = activitysite;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTicket() {
        return ticket;
    }

    public void setTicket(Double ticket) {
        this.ticket = ticket;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStick() {
        return stick;
    }

    public void setStick(String stick) {
        this.stick = stick;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
