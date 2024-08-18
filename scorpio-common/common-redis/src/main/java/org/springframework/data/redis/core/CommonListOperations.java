package org.springframework.data.redis.core;

import com.my.common.redis.operations.RedisOperations;

import java.util.List;
@SuppressWarnings("unused")
public class CommonListOperations<K, Object> extends DefaultListOperations<K, Object> implements RedisOperations {

    CommonListOperations(RedisTemplate template) {
        super(template);
    }

    public <V> V leftPop(K key, Class<V> clazz) {
        return om.convertValue(super.leftPop(key), clazz);
    }

    public <V> V rightPop(K key, Class<V> clazz) {
        return om.convertValue(super.rightPop(key), clazz);
    }

    public <V> List<V> range(K key, Long start, Long end, Class<V> clazz) {
        return convertList(super.range(key, start, end), clazz);
    }

}