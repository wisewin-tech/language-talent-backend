package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class QuestionBO extends BaseModel{
    private Integer id; //题库表
    private String topic; //题目
    private String option; //选项
    private String answer; //答案和解析
    private BigDecimal score; //分值
    private Integer chapterId; //课时id
    private Integer courseId; //课程id
    private String isCertificateExamination; //是否为证书考试题
    private String questionType; //判断（judge） 阅读（`read`） 课后测试:常规（common）翻译（translate） 拼写（`write`） 听力和文本匹配（hearingAndTest） 听音完成句子（hearingAndSentence） 图文匹配(imageText)
    private String type; //学前热身(warmUp)  课后测试（test）   能力检测(detection)
    private Integer createUserId; //创建人id
    private Integer updateUserId; //修改人id
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getIsCertificateExamination() {
        return isCertificateExamination;
    }

    public void setIsCertificateExamination(String isCertificateExamination) {
        this.isCertificateExamination = isCertificateExamination;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
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
