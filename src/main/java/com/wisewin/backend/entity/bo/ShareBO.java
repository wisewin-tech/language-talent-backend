package com.wisewin.backend.entity.bo;

public class ShareBO {
    private Integer id;
    private String key;
    private String value;
    private String rmark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRmark() {
        return rmark;
    }

    public void setRmark(String rmark) {
        this.rmark = rmark;
    }

    @Override
    public String toString() {
        return "ShareBO{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", rmark='" + rmark + '\'' +
                '}';
    }
}
