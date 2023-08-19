package com.my.common.mybatis.mapper;

import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface Mapper<T> extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        Marker {}