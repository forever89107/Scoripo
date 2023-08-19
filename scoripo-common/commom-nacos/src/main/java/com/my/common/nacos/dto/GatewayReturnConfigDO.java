package com.my.common.nacos.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GatewayReturnConfigDO implements Serializable {

    /**
     * 請求URl
     */
    private String url;

    /**
     * 返回數據
     */
    private JSONObject data;

}