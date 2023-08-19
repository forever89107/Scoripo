package com.my.common.mybatis.mapper;

import com.my.common.mybatis.provider.STDuplicateUpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.Collection;

@RegisterMapper
@SuppressWarnings("unused")
public interface STDuplicateSelectiveMapper<T> {

    @InsertProvider(
            type = STDuplicateUpdateProvider.class,
            method = "dynamicSQL"
    )
    int duplicateSelective(Collection<? extends T> var1);

}