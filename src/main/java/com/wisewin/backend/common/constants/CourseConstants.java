package com.wisewin.backend.common.constants;

/**
 * 课程
 */
public enum CourseConstants {
    /**
     *   可以考证
     */
    MAY("yes"),
    /**
     *   不可以考证
     */
    CANNOT("no");


    private CourseConstants(String value) {
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
