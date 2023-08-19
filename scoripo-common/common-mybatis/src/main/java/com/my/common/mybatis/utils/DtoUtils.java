package com.my.common.mybatis.utils;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;

@Slf4j
@SuppressWarnings("unused")
public class DtoUtils {

    public static <T> T transformBean(Object source, Class<T> targetClass, String... ignores)  {
        try {
            if(source == null){
                return null;
            }
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target, ignores);
            return target;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> transformList(List<?> source, Class<T> targetClass, String... ignores){
        if(source == null){
            return  null;
        }
        List<T> result = Lists.newArrayList();
        source.forEach(r -> {
            result.add(transformBean(r, targetClass, ignores));
        });
        return result;
    }

    public static <T, R> List<R> transformList(List<T> source, Function<T, R> function) {
        if(source == null) {
            return null;
        }
        List<R> result = Lists.newArrayList();
        source.forEach(t -> result.add(function.apply(t)));
        return result;
    }

    public static <T> PageInfo<T> transformPageInfo(PageInfo<?> source, Class<T> targetClass, String... ignores){
        if(source == null){
            return  null;
        }
        PageInfo<T> result = new PageInfo<>();
        if(source.getList() == null){
            BeanUtils.copyProperties(source, result, new String[]{"list"});
            return result;
        }

        List<T> list = Lists.newArrayList();
        result.getList().forEach(r -> {
            list.add(transformBean(r, targetClass, ignores));
        });
        result.setList(list);
        return result;
    }
}