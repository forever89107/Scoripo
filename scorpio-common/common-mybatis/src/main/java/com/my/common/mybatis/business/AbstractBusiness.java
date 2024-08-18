package com.my.common.mybatis.business;

import com.my.common.mybatis.mapper.Mapper;
import com.my.exception.GlobalException;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unused")
public class AbstractBusiness<T> implements IBusiness<T> {

    @Lazy
    @Autowired
    protected Mapper<T> mapper;

    /**
     * 當前泛型真實類型的Class
     */
    private final Class<T> modelClass;

    @SuppressWarnings("unchecked")
    public AbstractBusiness() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T model) {
        mapper.insertSelective(model);
    }

    @Override
    public void save(List<T> models) {
        mapper.insertList(models);
    }

    @Override
    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void updateByConditionSelective(T model, Condition condition) {
        mapper.updateByConditionSelective(model, condition);
    }

    @Override
    public int updateByPrimaryKey(T model) {
        return mapper.updateByPrimaryKey(model);
    }

    @Override
    public T findById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws GlobalException {
        try {
            T model = modelClass.getDeclaredConstructor().newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new GlobalException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAllBy(String fieldName, Object value) throws GlobalException {
        try {
            T model = modelClass.getDeclaredConstructor().newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.select(model);
        } catch (ReflectiveOperationException e) {
            throw new GlobalException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    @Override
    public List<T> findByCondition(Condition condition, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(condition, rowBounds);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

}