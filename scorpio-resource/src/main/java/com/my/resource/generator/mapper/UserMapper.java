package com.my.resource.generator.mapper;

import com.my.resource.generator.entity.OrmUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MBG
 * @since 2022-10-03 02:27:38
 */
@Mapper
public interface UserMapper extends BaseMapper<OrmUser> {

}
