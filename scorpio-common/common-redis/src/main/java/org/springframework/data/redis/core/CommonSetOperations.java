package org.springframework.data.redis.core;

import com.fasterxml.jackson.databind.JavaType;
import com.my.common.redis.operations.RedisOperations;

import java.util.Set;
@SuppressWarnings("unused")
public class CommonSetOperations<K,Object> extends DefaultSetOperations<K,Object> implements RedisOperations {

    CommonSetOperations(RedisTemplate template) {
        super(template);
    }

    public <V> Set<V> get(K key, Class<V> clazz){
        return  om.convertValue(super.members(key),getCollectionType(Set.class,clazz));
    }

    public <V> Set<V> get(K key, JavaType clazz){
        return  om.convertValue(super.members(key),getCollectionType(Set.class,clazz));
    }

}