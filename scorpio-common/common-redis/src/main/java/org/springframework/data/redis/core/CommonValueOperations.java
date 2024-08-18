package org.springframework.data.redis.core;

import com.my.common.redis.operations.RedisOperations;

import java.util.List;
@SuppressWarnings("unused")
public class CommonValueOperations<K, Object> extends DefaultValueOperations<K, Object> implements RedisOperations {

    CommonValueOperations(RedisTemplate template) {
        super(template);
    }

    public <V> V get(K key, Class<V> clazz) {
        return om.convertValue(super.get(key), clazz);
    }

    public <V> List<V> getList(K key, Class<V> clazz) {
        return convertList(super.get(key), clazz);
    }

}