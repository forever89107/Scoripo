package com.my.base;

public interface LocalKey {

    /**
     * 配置所屬的模塊
     */
    String getModule();

    /**
     * 配置的key
     */
    String getKey();

    /**
     * 解析方式
     */

    default String getConfigKey(){
        return getModule()+getKey();
    }
}
