package com.xjt.core.persistence;
/**
 * Title:  查询分页Dialect的MySQL实现
 * Description: 查询分页Dialect的MySQL实现
 */
public class MySQLDialect implements Dialect {

	public String getSql(String query, int offset, int limit){
        
        String sql = query.trim();
        boolean isForUpdate = false;
        if(sql.toLowerCase().endsWith(" for update"))
        {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        pagingSelect.append("select * from ( ");
        pagingSelect.append(sql);
        pagingSelect.append(" ) TTT LIMIT ").append(offset).append(",").append(limit);
        if(isForUpdate)
            pagingSelect.append(" for update");
        return pagingSelect.toString();
    }

}
