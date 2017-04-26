package com.xjt.core.persistence;

import java.sql.Statement;
import java.util.Properties;

//import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
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
@Intercepts( {@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class PagingResultSetHandlerInterceptor implements Interceptor{
   
    public Object intercept(Invocation invocation) throws Throwable
    {
        //FastResultSetHandler resultSet = (FastResultSetHandler)invocation.getTarget();
    	ResultSetHandler resultSet = (ResultSetHandler)invocation.getTarget();
        ReflectUtil.setFieldValue(resultSet, "rowBounds", new RowBounds());
        return invocation.proceed();
    }
   
    public Object plugin(Object target){
        return Plugin.wrap(target, this);
    }
   
    public void setProperties(Properties properties){
    }
}

