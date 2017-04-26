package com.xjt.core.persistence;

/**
 * <p>Title:  查询分页Dialect的Oracle实现</p>
 * <p>Description: 查询分页Dialect的Oracle实现</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class OracleDialect implements Dialect {
	
	/**
     * <p>Discription:Oracle分页</p>
     * @param query sql语句
     * @param offset 开始返回行之前忽略多少行
     * @param limit 显示数据条数
     * @return 分页后的sql
     * @author 刘宇 2011-3-23
     * @update [修改人] [修改时间] [变更描述]
     */
    public String getSql(String query, int offset, int limit){
        int begin = offset;
        int end = offset+limit;
        
        String sql = query.trim();
        boolean hasOffset = offset>0;
        boolean isForUpdate = false;
        if(sql.toLowerCase().endsWith(" for update"))
        {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        if(hasOffset)
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        else
            pagingSelect.append("select * from ( ");
        pagingSelect.append(sql);
        if(hasOffset)
            pagingSelect.append(" ) row_ ) where rownum_ <= ").append(end).append(" and rownum_ > ").append(begin);
        else
            pagingSelect.append(" ) where rownum <= ").append(end);
        if(isForUpdate)
            pagingSelect.append(" for update");
        return pagingSelect.toString();
    }

}
