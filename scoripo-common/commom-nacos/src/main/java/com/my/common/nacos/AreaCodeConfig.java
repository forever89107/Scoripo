package com.my.common.nacos;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.my.dto.AreaCodeConfigDO;
import com.my.dto.AreaCodeDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

@Slf4j
@Component
@SuppressWarnings("unused")
public class AreaCodeConfig {

    @Autowired
    StNacosConfigManager stNacosConfigManager;

    private final List<String> commonAreaCode = Lists.newArrayList();

    private final List<AreaCodeConfigDO> areaCodeList = Lists.newArrayList();
    private final List<AreaCodeConfigDO> commonList = Lists.newArrayList();
    private final Set<String> areaCodeSet = Sets.newHashSet();

    /**
     * 檢查是否是常用的有效區號
     */
    public boolean checkSupportCommonAreaCode(final String telArea) {
        return commonAreaCode.contains(telArea);
    }

    /**
     * 檢查區號是否支持
     */
    public boolean checkSupportAreaCode(final String telArea) {
        if (CollectionUtils.isEmpty(areaCodeSet)) {
            return false;
        }
        return areaCodeSet.contains(telArea);
    }

    public List<AreaCodeDO> getBackendCommonAreaCode() {
        List<AreaCodeDO> resultList = Lists.newArrayList();
        for (AreaCodeConfigDO codeConfig : commonList) {
            if (!codeConfig.getIsBackend()) {
                continue;
            }
            resultList.add(new AreaCodeDO(codeConfig.getCode(), codeConfig.getZhName(), codeConfig.getEnName(), codeConfig.getVietnamName(), codeConfig.getThaiName(), codeConfig.getJapaneseName(), codeConfig.getPortugueseName(), codeConfig.getSpanishName(), codeConfig.getArabicName()));
        }
        return resultList;
    }

    public List<AreaCodeDO> getForeheadCommonAreaCode() {
        List<AreaCodeDO> resultList = Lists.newArrayList();
        for (AreaCodeConfigDO codeConfig : commonList) {
            if (!codeConfig.getIsForehead()) {
                continue;
            }
            resultList.add(new AreaCodeDO(codeConfig.getCode(), codeConfig.getZhName(), codeConfig.getEnName(), codeConfig.getVietnamName(), codeConfig.getThaiName(), codeConfig.getJapaneseName(), codeConfig.getPortugueseName(), codeConfig.getSpanishName(), codeConfig.getArabicName()));
        }
        return resultList;
    }

    public List<AreaCodeDO> getBackendAreaCode() {
        List<AreaCodeDO> resultList = Lists.newArrayList();
        for (AreaCodeConfigDO codeConfig : areaCodeList) {
            if (!codeConfig.getIsBackend()) {
                continue;
            }
            resultList.add(new AreaCodeDO(codeConfig.getCode(), codeConfig.getZhName(), codeConfig.getEnName(), codeConfig.getVietnamName(), codeConfig.getThaiName(), codeConfig.getJapaneseName(), codeConfig.getPortugueseName(), codeConfig.getSpanishName(), codeConfig.getArabicName()));
        }
        return resultList;
    }

    public List<AreaCodeDO> getForeheadAreaCode() {
        List<AreaCodeDO> resultList = Lists.newArrayList();
        for (AreaCodeConfigDO codeConfig : areaCodeList) {
            if (!codeConfig.getIsForehead()) {
                continue;
            }
            resultList.add(new AreaCodeDO(codeConfig.getCode(), codeConfig.getZhName(), codeConfig.getEnName(), codeConfig.getVietnamName(), codeConfig.getThaiName(), codeConfig.getJapaneseName(), codeConfig.getPortugueseName(), codeConfig.getSpanishName(), codeConfig.getArabicName()));
        }
        return resultList;
    }

    private void updateAreaConfig(String source) {
        synchronized (areaCodeList) {
            areaCodeList.clear();
            areaCodeSet.clear();
            commonList.clear();
            JSONArray areaArray = JSON.parseArray(source);
            for (int i = 0, len = areaArray.size(); i < len; i++) {
                JSONObject areaObj = areaArray.getJSONObject(i);
                AreaCodeConfigDO areaCodeConfig = new AreaCodeConfigDO();
                areaCodeConfig.setCode(areaObj.getString("code"));
                areaCodeConfig.setEnName(areaObj.getString("en_name"));
                areaCodeConfig.setZhName(areaObj.getString("zh_name"));
                areaCodeConfig.setVietnamName(areaObj.getString("vietnamName"));
                areaCodeConfig.setThaiName(areaObj.getString("thaiName"));
                areaCodeConfig.setJapaneseName(areaObj.getString("japaneseName"));
                areaCodeConfig.setPortugueseName(areaObj.getString("portugueseName"));
                areaCodeConfig.setSpanishName(areaObj.getString("spanishName"));
                areaCodeConfig.setArabicName(areaObj.getString("arabicName"));
                areaCodeConfig.setIsBackend(areaObj.getBoolean("isBackend"));
                areaCodeConfig.setIsForehead(areaObj.getBoolean("isForehead"));
                areaCodeList.add(areaCodeConfig);
                areaCodeSet.add(areaCodeConfig.getCode());
                if (areaCodeConfig.getIsForehead()) {
                    commonAreaCode.add(areaCodeConfig.getCode());
                    commonList.add(areaCodeConfig);
                }
            }
        }
    }

    @PostConstruct
    private void init() {
        try {
            NacosConfigProperties properties = stNacosConfigManager.getNacosConfigProperties();
            ConfigService configService = stNacosConfigManager.getConfigService();
            String ruleDataId = "area-code-config";
            configService.addListener(ruleDataId, properties.getGroup(), new RuleListener());
            String ruleSource = configService.getConfig(ruleDataId, properties.getGroup(), properties.getTimeout());
            log.debug("規則加載成功:{}", ruleSource);
            updateAreaConfig(ruleSource);
        } catch (NacosException e) {
            log.error("AreaCodeHandler.init()", e);
        }
    }

    class RuleListener implements Listener {

        @Override
        public Executor getExecutor() {
            return null;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            log.debug("RuleListener收到的內容是{}", configInfo);
            updateAreaConfig(configInfo);
        }
    }

}