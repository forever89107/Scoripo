package org.springframework.data.redis.core;

import com.my.common.redis.operations.RedisOperations;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class CommonHashOperations<K, HK, Object> extends DefaultHashOperations<K, HK, Object> implements RedisOperations {

    CommonHashOperations(RedisTemplate template) {
        super(template);
    }

    public <HV> HV get(K key, HK field, Class<HV> clazz) {
        return om.convertValue(super.get(key, field), clazz);
    }

    public <HV> List<HV> getList(K key, HK field, Class<HV> clazz) {
        return convertList(super.get(key, field), clazz);
    }

    public <HV> Map<HK, HV> getMap(K key, HK field, Class<HV> clazz) {
        return om.convertValue(super.entries(key), getCollectionType(Map.class, clazz));
    }

    public <HV> Map<HK, HV> entries(K key, Class<HV> clazz) {
        return om.convertValue(super.entries(key), getMapType(Map.class, clazz));
    }

    public <HV> List<HV> multiGet(K key, Collection<HK> fields, Class<HV> clazz) {
        List<Object> objects = super.multiGet(key, fields);
        return convertList(objects, clazz);
    }

    public <HV> List<HV> multiGetList(K key, Collection<HK> fields, Class<HV> clazz) {
        List<Object> objects = super.multiGet(key, fields);
        return convertList(objects, clazz);
    }

    public <HV> List<HV> getAll(K key, Class<HV> clazz) {
        Map<HK, HV> entries = entries(key, clazz);
        if (ObjectUtils.isEmpty(entries)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(entries.values());
    }

}