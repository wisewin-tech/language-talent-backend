package com.wisewin.backend.common.constants;

/**
 * 语言
 */
public enum LanguageConstants {
    /**
     *   状态上架
     */
    STATUS_PUTAWAY("putaway"),
    /**
     *   状态下架
     */
    STATUS_SOLDOUT("soldout");




    private LanguageConstants(String value) {
        this.value = value;
    }
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }




}
