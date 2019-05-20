package com.wisewin.backend.entity.bo.common.constants;

public enum QuestionConstants {

    /*判断题*/JUDGE("judge"),
    /*阅读题*/READ("read"),
    /*单选题*/COMMON("common"),
    /*翻译题*/TRANSLATE("translate"),
    /*拼写题*/WRITE("write"),
    /*听力和文本匹配*/HEARINGANDTEST("hearingAndTest"),
    /*听音完成句子*/HEARINGANDSENTENCE("hearingAndSentence"),
    /*图文匹配*/IMAGETEXT("imageText"),

    /*语言能力测试*/LANGUAGETEST("languageTest"),
    /*课程考证题*/COURSECERTIFICATE("courseCertificate"),
    /*学前热身*/WARMUP("warmUp"),
    /*课后测试*/TEST("test");


    private QuestionConstants(String value) {
        this.value = value;
    }
    private QuestionConstants(Integer num) {
        this.num = num;
    }

    private String value;
    private Integer num;
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
