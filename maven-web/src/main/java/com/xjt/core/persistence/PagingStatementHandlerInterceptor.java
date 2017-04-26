package com.xjt.core.persistence;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

/**
 * <p>Title: 查询分页插件 </p>
 * <p>Description: 查询分页插件 </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
@Intercepts( {@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PagingStatementHandlerInterceptor implements Interceptor {
	
	private String dialect;
	private int limit;
	
    public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler statement = (RoutingStatementHandler)invocation.getTarget();
        Object object = ReflectUtil.getFieldValue(statement, "delegate");
        if(object instanceof PreparedStatementHandler){
        	BoundSql boundSql = statement.getBoundSql();
        	String sql = boundSql.getSql();
        	//翻页
        	sql = paging(object, sql);
            ReflectUtil.setFieldValue(boundSql, "sql", sql);
        }
        return invocation.proceed();
    }

	private String paging(Object object, String sql) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		PreparedStatementHandler handler = (PreparedStatementHandler)object;
		RowBounds rowBounds = (RowBounds)ReflectUtil.getFieldValue(handler, "rowBounds");
	    int _limit = rowBounds.getLimit();
	    if(_limit != RowBounds.NO_ROW_LIMIT){
	    	
		    int dialectlimit = getLimit();
		    if(_limit<0){
		    	if(dialectlimit>0){
		    		_limit = dialectlimit;
		    	}else{
		    		_limit = RowBounds.NO_ROW_LIMIT;
		    	}
		    }else{
		    	if(dialectlimit>0 && _limit>dialectlimit){
		    		_limit = dialectlimit;
		    	}
		    }
		    
		    Dialect dialectObj = (Dialect)Class.forName(dialect).newInstance();
		    sql = dialectObj.getSql(sql, rowBounds.getOffset(), _limit);
	    }
		return sql;
	}
   
    public Object plugin(Object target){
        return Plugin.wrap(target, this);
    }
   
    public void setProperties(Properties properties){
    	setDialect(String.valueOf(properties.get("dialect")));
    	setLimit(Integer.parseInt(String.valueOf(properties.get("limit"))));
    }
    
}

