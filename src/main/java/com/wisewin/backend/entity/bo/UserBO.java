package com.wisewin.backend.entity.bo;

public class UserBO  {
    private Integer id; //用戶表
    private String name; //姓名
    private String nickname; //昵称
    private String password; //密码
    private String email; //邮箱
    private String mobile; //手机号
    private Integer age; //年龄
    private String ageGroup; //年龄段
    private String headPortraitUrl; //头像路径
    private String sex; //性别
    private String birthday; //生日
    private Integer inviteNumber; //邀请人数
    private String source; //个人/企业
    private String accountBalance; //账户余额
    private String integral; //积分
    private String kadou; //咖豆
    private String inviteCode; //邀请码
    private String byInvite; //被邀请码
    private String job; //职业
    private String qqOpenid; //qqid
    private String wxOpenid; //微信id

    @Override
    public String toString() {
        return "UserBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", age=" + age +
                ", ageGroup='" + ageGroup + '\'' +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", inviteNumber=" + inviteNumber +
                ", source='" + source + '\'' +
                ", accountBalance='" + accountBalance + '\'' +
                ", integral='" + integral + '\'' +
                ", kadou='" + kadou + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", byInvite='" + byInvite + '\'' +
                ", job='" + job + '\'' +
                ", qqOpenid='" + qqOpenid + '\'' +
                ", wxOpenid='" + wxOpenid + '\'' +
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getInviteNumber() {
        return inviteNumber;
    }

    public void setInviteNumber(Integer inviteNumber) {
        this.inviteNumber = inviteNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getKadou() {
        return kadou;
    }

    public void setKadou(String kadou) {
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
}
