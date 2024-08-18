package com.my.common.mybatis.common;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@SuppressWarnings("unused")
public class MyBatisSQL {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> executeSQL(String sql) {
        return sqlSessionTemplate.selectList("com.my.common.mybatis.common.DynamicMapper.customQuery", sql);
    }

    public int executeUpdate(String sql) {
        return sqlSessionTemplate.update("com.my.common.mybatis.common.DynamicMapper.customUpdate", sql);
    }

    public int executeDelete(String sql) {
        return sqlSessionTemplate.delete("com.my.common.mybatis.common.DynamicMapper.customDelete", sql);
    }

    public int executeInsert(String sql) {
        return sqlSessionTemplate.delete("com.my.common.mybatis.common.DynamicMapper.customInsert", sql);
    }

}