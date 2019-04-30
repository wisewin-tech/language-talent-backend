package com.wisewin.backend.entity.param;

public class LanguageParam {

    private String languageName ;//语言名字
    private String status ;// 状态  (上/下架) putaway / soldout,
    private String hotOrNot;// are /no
    private String preference;//优惠中

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHotOrNot() {
        return hotOrNot;
    }

    public void setHotOrNot(String hotOrNot) {
        this.hotOrNot = hotOrNot;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }
}
