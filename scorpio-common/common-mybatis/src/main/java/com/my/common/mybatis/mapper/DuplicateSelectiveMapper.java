package com.my.common.mybatis.mapper;

import com.my.common.mybatis.provider.DuplicateUpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.Collection;

@RegisterMapper
@SuppressWarnings("unused")
public interface DuplicateSelectiveMapper<T> {

    @InsertProvider(
            type = DuplicateUpdateProvider.class,
            method = "dynamicSQL"
    )
    int duplicateSelective(Collection<? extends T> var1);

}