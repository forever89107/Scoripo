package com.my.common.http;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class UrlUtil {

    @Getter
    private static final UrlUtil instance = new UrlUtil();

    private UrlUtil() {

    }

    /**
     * Get Param by URL
     */
    public Map<String, String> getParam(final String url) {
        Map<String, String> params = new ConcurrentHashMap<>();
        try {
            URL urlObj = new URL(url);
            String query = urlObj.getQuery();
            String[] datas = query.split("&");
            for (String data : datas) {
                if (StringUtils.isEmpty(data)) {
                    continue;
                }
                String[] paramArray = data.split("=");
                if (paramArray.length < 2) {
                    continue;
                }
                params.put(paramArray[0], paramArray[1]);
            }
        } catch (Exception e) {
            log.error("UrlUtil.getParam", e);
        }
        return params;
    }

    /**
     * 取代URL某參數param的值
     */
    public static String replaceParam(String url, String paramName, String paramVal) {
        String result = url;
        try {
            Matcher matcher = Pattern.compile("(?<=" + paramName + "=)[^&]*").matcher(url);
            if (matcher.find()) {
                result = url.replace(paramName + "=" + matcher.group(), paramName + "=" + paramVal);
            }
        } catch (Exception e) {
            log.error(" replaceParam error: {}, url:{} ", e.getMessage(), url, e);
        }
        return result;
    }
}
