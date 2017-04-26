package com.xjt.core.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.xjt.core.exception.SystemException;

/**
 * <p>Title: SqlSession工具类 </p>
 * <p>Description: SqlSession工具类 </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class SqlSessionHelper {
	
	private static Log log = LogFactory.getLog(SqlSessionHelper.class);
	
	private static final Object[] getMappedSqlParameterObjectArray(String sqlId, Object parameterObject, SqlSession sqlSession) {
		List objectList = new ArrayList();
		
		Configuration configuration = sqlSession.getConfiguration();
		MappedStatement mappedStatement = configuration.getMappedStatement(sqlId);
		BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
		TypeHandlerRegistry typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();

		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(
									propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					
					objectList.add(value);
				}
			}
		}
		
		return objectList.toArray();
	}
	
	/**
	 * 根据查询条件进行查询分页
	 * @param sqlId sql标识
	 * @param queryFilter 查询条件对象
	 * @param sqlSession 数据库连接句柄
	 * @return
	 */
	public static final List selectForPaging(String sqlId, QueryFilter queryFilter, SqlSession sqlSession) {
		if(queryFilter==null){
			queryFilter = new QueryFilter();
			queryFilter.setPageNumber(1);
			queryFilter.setPageSize(10);
		}
		
		int pageNo = queryFilter.getPageNumber();
		int pageSize = queryFilter.getPageSize();
		if (pageNo <= 0)
			pageNo = 1;
		if (pageSize == 0)
			pageSize = 10;
		int offset = (pageNo - 1) * pageSize;
		List list = sqlSession.selectList(sqlId, queryFilter, new RowBounds(offset, pageSize));
		int recordCount = 0;
		if (list.size() > 0) {
			//if(queryFilter.getTotalCount()==0){
				try {
					String sql = sqlSession.getConfiguration().getMappedStatement(sqlId).getBoundSql(queryFilter).getSql();
					sql = "SELECT COUNT(1) FROM (" + sql + ") TTT";
					Object[] parameterObjectArray = getMappedSqlParameterObjectArray(sqlId, queryFilter, sqlSession);
					SqlRunner sqlRunner = new SqlRunner(sqlSession.getConnection());
					Map resultMap = sqlRunner.selectOne(sql, parameterObjectArray);
					recordCount = Integer.parseInt(String.valueOf(resultMap.values().toArray()[0]));
					queryFilter.setTotalCount(recordCount);
				} catch (SQLException e) {
					log.error("查询分页获得总记录条数时出错", e);
					throw new SystemException(e);
				}
			//}
		}
		return list;
	}

}
