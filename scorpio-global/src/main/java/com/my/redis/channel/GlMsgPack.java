package com.my.redis.channel;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

public class GlMsgPack {

    // pack
    public static String pack(Channel channel, Object data) {
        HashMap<String, Object> msg = new HashMap<>();
        msg.put("chId", channel.value());
        msg.put("data", data);
        return JSON.toJSONString(msg);
    }
}
