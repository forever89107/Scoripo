package com.my.common.nacos;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StNacosConfigManager extends NacosConfigManager {

    @Autowired
    public StNacosConfigManager(NacosConfigProperties nacosConfigProperties) {
        super(nacosConfigProperties);
    }
}
