package com.xjt.core.persistence;

/**
 * <p>Title: 查询分页的查询条件基础类 </p>
 * <p>Description: 查询分页的查询条件基础类 </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class QueryFilter {
	
	/**
	 * 当前页码
	 */
    private int pageNumber;
	
    /**
     * 每页显示多少条 
     */
	private int pageSize;
	
	/**
	 * 记录总条数
	 */
	private int totalCount;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
