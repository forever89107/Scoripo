package com.my.common.nacos.adapter.base;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.my.common.nacos.StNacosConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * 適用於多語言配置
 */
@Slf4j
public abstract class AbstractBaseConfigChangeAdapter implements ChangeListener {

    @Autowired
    private StNacosConfigManager stNacosConfigManager;

    public abstract Set<String> getDataIds();

    @PostConstruct
    private void init() {
        NacosConfigProperties properties = stNacosConfigManager.getNacosConfigProperties();
        ConfigService configService = stNacosConfigManager.getConfigService();
        getDataIds().forEach(dataId -> {
            try {
                configService.addListener(dataId, properties.getGroup(), RuleListener.builder()
                        .dataId(dataId)
                        .changeListener(this)
                        .build());
                String configSource = configService.getConfig(dataId, properties.getGroup(), properties.getTimeout());
                log.debug("规则加载成功:{}", configSource);
                onChanged(dataId,configSource);
            } catch (NacosException e) {
                e.printStackTrace();
                log.error("该id加载失败："+dataId,e);
            }
        });
    }

}
