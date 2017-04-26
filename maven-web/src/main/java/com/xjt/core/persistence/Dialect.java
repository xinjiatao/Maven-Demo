package com.xjt.core.persistence;

/**
 * <p>Title:  查询分页Dialect接口</p>
 * <p>Description: 查询分页Dialect接口</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public interface Dialect {

	/**
	 * <p>Discription:获得物理查询的sql语句</p>
	 * @param sql 查询的sql语句
	 * @param offset 开始返回行之前忽略多少行
	 * @param limit 显示数据条数
	 * @return
	 * @author 刘宇 2011-4-19
	 * @update [修改人] [修改时间] [变更描述]
	 */
    String getSql(String sql, int offset, int limit);
    
}
