package com.xjt.core.persistence;


/**
 * <p>Title:  查询分页Dialect的DB2实现</p>
 * <p>Description: 查询分页Dialect的DB2实现</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class DB2Dialect implements Dialect {
	
	private String getRowNumber(String sql)
    {
        StringBuffer rownumber = (new StringBuffer(50)).append("rownumber() over(");
        int orderByIndex = sql.toLowerCase().indexOf("order by");
        if(orderByIndex > 0 && !hasDistinct(sql))
            rownumber.append(sql.substring(orderByIndex));
        rownumber.append(") as rownumber_,");
        return rownumber.toString();
    }
	
	private boolean hasDistinct(String sql)
    {
        return sql.toLowerCase().indexOf("select distinct") >= 0;
    }

	/**
     * <p>Discription:DB2分页</p>
     * @param query sql语句
     * @param offset 开始返回行之前忽略多少行
     * @param limit 显示数据条数
     * @return 分页后的sql
     * @author 刘宇 2011-3-23
     * @update [修改人] [修改时间] [变更描述]
     */
    public String getSql(String query, int offset, int limit){
    	if(query.toUpperCase().contains("UNION")){
    		query = "SELECT query_.* FROM (" + query + ") as query_";
    	}
    	
        int begin = offset;
        int end = offset+limit;
        
        String sql = query.trim();
        boolean hasOffset = offset>0;
        
        int startOfSelect = sql.toLowerCase().indexOf("select");
        StringBuffer pagingSelect = (new StringBuffer(sql.length() + 100)).append(sql.substring(0, startOfSelect)).append("select * from ( select ").append(getRowNumber(sql));
        if(hasDistinct(sql))
            pagingSelect.append(" row_.* from ( ").append(sql.substring(startOfSelect)).append(" ) as row_");
        else
            pagingSelect.append(sql.substring(startOfSelect + 6));
        pagingSelect.append(" ) as temp_ where rownumber_ ");
        if(hasOffset)
        	pagingSelect.append("between ").append(begin+1).append(" and ").append(end);
        else
        	pagingSelect.append("<= ").append(end);
        return pagingSelect.toString();
    }

}