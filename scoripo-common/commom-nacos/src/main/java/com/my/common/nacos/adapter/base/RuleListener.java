package com.my.common.nacos.adapter.base;

import com.alibaba.nacos.api.config.listener.Listener;
import lombok.Builder;

import java.util.concurrent.Executor;

@Builder
public class RuleListener implements Listener {
    private String dataId;

    public ChangeListener changeListener;

    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void receiveConfigInfo(String source) {
        changeListener.onChanged(dataId,source);
    }
}
