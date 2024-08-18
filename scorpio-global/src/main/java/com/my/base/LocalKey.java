package com.my.base;

public interface LocalKey {

    String getModule();

    String getKey();

    default String getConfigKey() {
        return getModule() + getKey();
    }

    default String getDesc() {
        return getKey();
    }
}
