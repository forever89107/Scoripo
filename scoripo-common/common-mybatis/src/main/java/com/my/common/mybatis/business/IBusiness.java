package com.my.common.mybatis.business;

import com.my.exception.GlobalException;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
@SuppressWarnings("unused")
public interface IBusiness<T> {

    /**
     * 持久化
     */
    void save(T model);

    /**
     * 批量持久化
     */
    void save(List<T> models);

    /**
     * 通過主鍵刪除
     */
    void deleteById(Object id);

    /**
     * 批量刪除
     * eg：ids -> “1,2,3,4”
     */
    void deleteByIds(String ids);

    /**
     * 更新
     */
    void updateByPrimaryKeySelective(T model);

    /**
     * 更新
     */
    void updateByConditionSelective(T model, Condition condition);

    /**
     * 全量更新
     */
    int updateByPrimaryKey(T model);

    /**
     * 通過ID查找
     */
    T findById(Object id);

    /**
     * 通過Model中某個成員變量名稱（非數據表中column的名稱）查找,value需符合unique約束
     */
    T findBy(String fieldName, Object value) throws GlobalException;

    /**
     * 通過滿足熟悉條件的全部數據
     */
    List<T> findAllBy(String fieldName, Object value) throws GlobalException;

    /**
     * 通過多個ID查找
     * eg：ids -> “1,2,3,4”
     */
    List<T> findByIds(String ids);

    /**
     * 根據條件查找
     */
    List<T> findByCondition(Condition condition);

    /**
     * 根據條件查找
     */
    List<T> findByCondition(Condition condition, RowBounds rowBounds);

    /**
     * 獲取所有
     */
    List<T> findAll();

}