package scorpio.core.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class EmptyUtil {
    public EmptyUtil() {
    }

    public static boolean stringIsEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean stringIsNotEmpty(String str) {
        return !stringIsEmpty(str);
    }

    public static <T> boolean listIsEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean listIsNotEmpty(List<T> list) {
        return !listIsEmpty(list);
    }

    public static <T> boolean arrayIsEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <K, V> boolean mapIsEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> boolean mapIsNotEmpty(Map<K, V> map) {
        return !mapIsEmpty(map);
    }

    public static boolean idIsEmpty(String id) {
        return stringIsEmpty(id);
    }

    public static boolean idIsNotEmpty(String id) {
        return !idIsEmpty(id);
    }

    public static boolean idisValidNotNull(Integer id) {
        return id != null && id > 0;
    }

    public static boolean idIsValidWithNull(Integer id) {
        return id == null || idisValidNotNull(id);
    }
}
