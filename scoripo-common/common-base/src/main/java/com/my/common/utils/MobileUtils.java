package com.my.common.utils;

import org.springframework.util.StringUtils;

public class MobileUtils {

    public String getMobile(String mobile) {
        if (!StringUtils.hasLength(mobile)) {
            return null;
        }
        return mobile.replaceAll("^(0+)", "");
    }

    public String getAreaCode(String areaCode) {
        if (!StringUtils.hasLength(areaCode)) {
            return areaCode;
        }
        return areaCode.replaceAll("\\+", "");
    }
}
