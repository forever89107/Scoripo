package com.my.common.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.provider.SpecialProvider;

import java.util.Set;
@SuppressWarnings("unused")
public class STDuplicateUpdateProvider extends SpecialProvider {

    public STDuplicateUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }
    public String duplicateSelective(MappedStatement ms) {
        StringBuilder sql = new StringBuilder(insertList(ms));
        sql.append(" ON DUPLICATE KEY UPDATE ");
        sql.append(" <trim suffixOverrides=\",\"> ");

        Class<?> entityClass = this.getEntityClass(ms);
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        columnSet.forEach((tempColumn) -> {
            if (!tempColumn.isId()) {
                sql.append("<if test=\"null != list[0].");
                sql.append(tempColumn.getEntityField().getName());
                sql.append("\">");
                sql.append(tempColumn.getColumn() );
                sql.append(" = values(" );
                sql.append(tempColumn.getColumn() );
                sql.append("),</if>");
            }
        });
        sql.append("</trim>");
        return sql.toString();
    }

}
