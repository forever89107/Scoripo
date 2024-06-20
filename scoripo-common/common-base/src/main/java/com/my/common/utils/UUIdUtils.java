package com.my.common.utils;

import java.util.UUID;

public class UUIdUtils {

    public static String getUUIdUnsigned(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }

    public static String getUUId(){
        return UUID.randomUUID().toString();
    }

}
