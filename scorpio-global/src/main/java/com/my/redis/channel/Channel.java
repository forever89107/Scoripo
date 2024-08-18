package com.my.redis.channel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Channel {

    Test(400),



    ;

    private final int value;

    Channel(int value) {
        this.value = value;
    }

    @JsonValue
    public int value() {
        return this.value;
    }

}