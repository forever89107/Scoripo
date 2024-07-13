package com.my.user.mapper;

import com.my.common.mybatis.mapper.Mapper;
import com.my.user.model.SCUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SCUserMapper extends Mapper<SCUser> {
    @Select("select * from sc_user where id = #{id}")
    SCUser selectNumberOne(@Param("id") Long id);
}
