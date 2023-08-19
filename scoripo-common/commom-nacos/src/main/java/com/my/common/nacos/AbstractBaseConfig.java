package com.my.common.nacos;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

@Slf4j
@SuppressWarnings("unused")
public abstract class AbstractBaseConfig<T> {

    @Autowired
    private StNacosConfigManager stNacosConfigManager;

    public abstract String configId();

    public abstract T parseConfigSource(String source);

    private T config;


    public T getConfig(){
        Assert.isTrue(config != null,"配置加载失败");
        return config;
    }

    public void updateConfig(String configSource){
        config = parseConfigSource(configSource);
    }


    @PostConstruct
    private void init() {
        try {
            ConfigService configService = stNacosConfigManager.getConfigService();
            NacosConfigProperties properties = stNacosConfigManager.getNacosConfigProperties();
            configService.addListener(configId(), properties.getGroup(), new RuleListener());
            String configSource = configService.getConfig(configId(), properties.getGroup(), properties.getTimeout());
            log.debug("规则加载成功:{}", configSource);
            updateConfig(configSource);
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
            log.debug("RuleListener收到的内容是{}", configInfo);
            updateConfig(configInfo);
        }
    }
}
