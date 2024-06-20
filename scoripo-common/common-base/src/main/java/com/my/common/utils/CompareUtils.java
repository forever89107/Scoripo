package com.my.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompareUtils {

    public static List<String> getDifference(Object newItem, Object oldItem) {
        List<String> values = new ArrayList<>();
        try {
            for (Field field : newItem.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object newVal = field.get(newItem);
                Object oldVal = field.get(oldItem);
                if (!Objects.equals(newVal, oldVal)) {
                    values.add(field.getName());
                }
            }
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        }

        return values;
    }
}
