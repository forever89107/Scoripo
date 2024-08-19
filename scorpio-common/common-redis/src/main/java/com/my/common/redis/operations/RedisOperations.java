package com.my.common.redis.operations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public interface RedisOperations<K, V> {

    ObjectMapper objectMapper = new ObjectMapper() {
        {
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        @Override
        public <T> T convertValue(Object fromValue, Class<T> toValueType) throws IllegalArgumentException {
            if (ObjectUtils.isEmpty(fromValue)) {
                return null;
            }
            return super.convertValue(fromValue, toValueType);
        }

        @Override
        public <T> T convertValue(Object fromValue, JavaType toValueType) throws IllegalArgumentException {
            if (ObjectUtils.isEmpty(fromValue)) {
                return null;
            }
            return super.convertValue(fromValue, toValueType);
        }
    };

    /**
     * Convert an object to the specified type.
     *
     * @param from  the object to be converted
     * @param clazz the target class
     * @param <T>   the type parameter
     * @return the converted object of the specified type, or null if input is empty
     */
    default <T> T convertValue(Object from, Class<T> clazz) {
        return objectMapper.convertValue(from, clazz);
    }

    /**
     * Convert an object to a list of the specified type.
     *
     * @param from  the object to be converted
     * @param clazz the target class of the list elements
     * @param <T>   the type parameter
     * @return a list of the specified type, or null if input is empty
     */
    default <T> List<T> convertList(Object from, Class<T> clazz) {
        return objectMapper.convertValue(from, getCollectionType(Collection.class, clazz));
    }

    /**
     * Get the JavaType for a collection with the specified element type.
     *
     * @param collectionClazz the collection class (e.g., List, Set)
     * @param elementClazz    the element class
     * @param <T>             the type parameter
     * @return the JavaType for the specified collection type
     */
    default <T> JavaType getCollectionType(Class<? extends Collection> collectionClazz, Class<T> elementClazz) {
        return objectMapper.getTypeFactory().constructCollectionType(collectionClazz, elementClazz);
    }

    /**
     * Get the JavaType for a collection with the specified element type.
     *
     * @param collectionClazz the collection class (e.g., List, Set)
     * @param elementType     the element JavaType
     * @return the JavaType for the specified collection type
     */
    default JavaType getCollectionType(Class<? extends Collection> collectionClazz, JavaType elementType) {
        return objectMapper.getTypeFactory().constructCollectionType(collectionClazz, elementType);
    }

    /**
     * Get the JavaType for a Map with String keys and the specified value type.
     *
     * @param mapClazz   the map class (e.g., HashMap)
     * @param valueClazz the value class
     * @param <T>        the type parameter
     * @return the JavaType for the specified map type
     */
    default <T> JavaType getMapType(Class<? extends Map> mapClazz, Class<T> valueClazz) {
        return objectMapper.getTypeFactory().constructMapType(mapClazz, String.class, valueClazz);
    }

}
