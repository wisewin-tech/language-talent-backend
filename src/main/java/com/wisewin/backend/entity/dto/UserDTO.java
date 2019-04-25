package com.wisewin.backend.entity.dto;


import java.util.Date;

/**
 * 用户表
 */
public class UserDTO {
    private Integer id; //用戶表
    private String name; //姓名
    private String nickname; //昵称
    private String password; //密码
    private String email; //邮箱
    private String mobile; //手机号
    private String ageGroup; //年龄段
    private String headPortraitUrl; //头像路径
    private String sex; //性别
    private Date birthday; //生日
    private String source; //个人/企业
    private Integer integral; //积分
    private Integer kadou; //咖豆
    private String inviteCode; //邀请码
    private String byInvite; //被邀请码
    private String job; //职业
    private String qqOpenid; //qq登录id
    private String wxOpenid; //微信登录id
    private Integer continuousSign; //连续签到天数
    private Integer cumulativeSign; //累计签到天数
    private Date lastSign; //上次签到时间
    private Integer continuousLearning; //连续学习天数
    private Integer cumulativeLearning; //累计学习天数
    private Integer createId; //创建人id
    private Integer updateId; //修改人id
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    @Override
    public String toString() {
        return "UserBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", source='" + source + '\'' +
                ", integral=" + integral +
                ", kadou=" + kadou +
                ", inviteCode='" + inviteCode + '\'' +
                ", byInvite='" + byInvite + '\'' +
                ", job='" + job + '\'' +
                ", qqOpenid='" + qqOpenid + '\'' +
                ", wxOpenid='" + wxOpenid + '\'' +
                ", continuousSign=" + continuousSign +
                ", cumulativeSign=" + cumulativeSign +
                ", lastSign=" + lastSign +
                ", continuousLearning=" + continuousLearning +
                ", cumulativeLearning=" + cumulativeLearning +
                ", createId=" + createId +
                ", updateId=" + updateId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getKadou() {
        return kadou;
    }

    public void setKadou(Integer kadou) {
        this.kadou = kadou;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getByInvite() {
        return byInvite;
    }

    public void setByInvite(String byInvite) {
        this.byInvite = byInvite;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public Integer getContinuousSign() {
        return continuousSign;
    }

    public void setContinuousSign(Integer continuousSign) {
        this.continuousSign = continuousSign;
    }

    public Integer getCumulativeSign() {
        return cumulativeSign;
    }

    public void setCumulativeSign(Integer cumulativeSign) {
        this.cumulativeSign = cumulativeSign;
    }

    public Date getLastSign() {
        return lastSign;
    }

    public void setLastSign(Date lastSign) {
        this.lastSign = lastSign;
    }

    public Integer getContinuousLearning() {
        return continuousLearning;
    }

    public void setContinuousLearning(Integer continuousLearning) {
        this.continuousLearning = continuousLearning;
    }

    public Integer getCumulativeLearning() {
        return cumulativeLearning;
    }

    public void setCumulativeLearning(Integer cumulativeLearning) {
        this.cumulativeLearning = cumulativeLearning;
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
}
