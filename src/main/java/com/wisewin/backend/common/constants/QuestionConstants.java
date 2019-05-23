package com.wisewin.backend.common.constants;

/**
 * 课程
 */
public enum QuestionConstants {

//    判断（judge） 阅读（`read`） 课后测试:单选（common）翻译（translate） 拼写（`write`） 听力和文本匹配（hearingAndTest） 听音完成句子（hearingAndSentence） 图文匹配(imageText)

Sta("");

    private QuestionConstants(String value) {
        this.value = value;
    }
    private String value;

    public String getValue() {
        return value;
    }






}
