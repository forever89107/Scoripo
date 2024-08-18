package com.my.enumerate;

public enum ExportConfigEnum {

    RECORD("record", "記錄導出"),
    ;

    private final String name;

    private final String desc;

    ExportConfigEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getConfigName() {
        return name;
    }

    public String getConfigDesc() {
        return desc;
    }

}