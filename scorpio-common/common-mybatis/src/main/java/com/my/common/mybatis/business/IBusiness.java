package com.my.common.mybatis.business;

import com.my.exception.GlobalException;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
@SuppressWarnings("unused")
public interface IBusiness<T> {

    /**
     * Persist the model
     */
    void save(T model);

    /**
     * Batch persist
     */
    void save(List<T> models);

    /**
     * Delete by primary key
     */
    void deleteById(Object id);

    /**
     * Batch delete
     * eg: ids -> "1,2,3,4"
     */
    void deleteByIds(String ids);

    /**
     * Update selectively by primary key
     */
    void updateByPrimaryKeySelective(T model);

    /**
     * Update selectively by condition
     */
    void updateByConditionSelective(T model, Condition condition);

    /**
     * Update fully by primary key
     */
    int updateByPrimaryKey(T model);

    /**
     * Find by ID
     */
    T findById(Object id);

    /**
     * Find by a specific field in the model (not the column name in the database), the value must satisfy unique constraints
     */
    T findBy(String fieldName, Object value) throws GlobalException;

    /**
     * Find all by a specific field in the model
     */
    List<T> findAllBy(String fieldName, Object value) throws GlobalException;

    /**
     * Find by multiple IDs
     * eg: ids -> "1,2,3,4"
     */
    List<T> findByIds(String ids);

    /**
     * Find by condition
     */
    List<T> findByCondition(Condition condition);

    /**
     * Find by condition with pagination
     */
    List<T> findByCondition(Condition condition, RowBounds rowBounds);

    /**
     * Find all
     */
    List<T> findAll();

}