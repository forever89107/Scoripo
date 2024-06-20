package com.my.common.utils;

import lombok.Getter;

public class RandomValidateCodeUtil {

    /**
     * 4位數隨機數
     */
    public String getRandomNumber() {
        return (int) ((Math.random() * 9 + 1) * 1000)+"";
    }

    /**
     * 6位數隨機數
     */
    public String get6RandomNumber() {
        return (int) ((Math.random() * 9 + 1) * 100000)+"";
    }

    @Getter
    private static final RandomValidateCodeUtil instance = new RandomValidateCodeUtil();

    private RandomValidateCodeUtil() {

    }

}