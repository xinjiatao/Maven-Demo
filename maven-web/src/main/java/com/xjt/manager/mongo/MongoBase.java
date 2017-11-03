package com.xjt.manager.mongo;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.xjt.core.persistence.QueryFilter;


/**
 * <p>功能描述：MongoDB的CURD</p>
 * <p>类名称：com.tsp.core.mongo.MongoBase</p>
 * @author 辛加涛
 * @create 2016年12月5日 下午2:53:01
 */
@Component
public abstract class MongoBase<T> {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * @Title 继承的子类需实现此方法，返回实体对象类的Class
	 * @author 辛加涛
	 * @create 2016年12月5日 上午11:42:43
	 * @return
	 */
	public abstract Class<T> getEntityClass();
	
	/**
	 * @Title 单条插入
	 * @author 辛加涛
	 * @create 2016年12月4日 下午1:00:44
	 * @param objectToSave 实体对象
	 */
	public void insert(T objectToSave) {
		mongoTemplate.insert(objectToSave);
	}
	
	/**
	 * @Title 批量插入
	 * @author 辛加涛
	 * @create 2016年12月4日 下午1:01:12
	 * @param batchToSave 实体对象集合
	 */
	public void insertAll(Collection<T> batchToSave) {
		mongoTemplate.insert(batchToSave, this.getEntityClass());
	}
	
	/**
	 * @Title 根据条件删除
	 * @author 辛加涛
	 * @create 2016年12月5日 上午11:52:27
	 * @param query 查询条件
	 */
	public void remove(Query query) {
		mongoTemplate.remove(query, this.getEntityClass());
	}
	
	/**
	 * <p>Title: 根据条件查询，删除查询结果集的第一条记录</p>
	 * <p>Description: 如果查询无结果，那么什么也不做</p>
	 * @author 辛加涛
	 * @create 2016年12月5日 上午11:50:23
	 * @param query 查询条件
	 * @return 被删除的记录对象，如果没有查询结果则返回null
	 */
	public T findAndRemove(Query query) {
		return mongoTemplate.findAndRemove(query, this.getEntityClass());
	}
	
	/**
	 * @Title 根据条件查询，更新查询结果集的第一条记录
	 * @author 辛加涛
	 * @create 2016年12月5日 下午2:03:46
	 * @param query
	 * @param update
	 */
	public void updateFirst(Query query, Update update) {
		mongoTemplate.updateFirst(query, update, this.getEntityClass());
	}
	
	/**
	 * @Title 根据条件查询，更新查询结果集的全部记录
	 * @author 辛加涛
	 * @create 2016年12月5日 下午2:04:09
	 * @param query
	 * @param update
	 */
	public void updateMulti(Query query, Update update) {
		mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}
	
	/**
	 * @Title 根据条件查询，更新查询结果集的第一条记录，如果查询无结果，那么会插入一条新数据
	 * @author 辛加涛
	 * @create 2016年12月5日 下午2:14:57
	 * @param query
	 * @param update
	 */
	public void upsert(Query query, Update update) {
		mongoTemplate.upsert(query, update, this.getEntityClass());
	}
	
	/**
	 * <p>Title: 保存数据，如果数据已存在就是更新，如果数据不存在就是插入</p>
	 * <p>Description: 如果想插入数据，keyId一定不要赋值或set操作，相反如果想更新数据，keyId一定要赋值或set操作</p>
	 * @param objectToSave
	 * @return
	 * @author 辛加涛
	 * @create 2016年12月23日 下午4:57:27
	 */
	public T save(T objectToSave) {
		mongoTemplate.save(objectToSave);
		return objectToSave;
	}
	
	/**
	 * <p>Title: 根据条件查询，修改查询结果集的第一条记录</p>
	 * <p>Description: 如果查询无结果，那么什么也不做</p>
	 * @author 辛加涛
	 * @create 2016年12月5日 下午2:32:14
	 * @param query
	 * @param update
	 * @return 被修改的记录对象（返回对象被修改前的值），如果没有查询结果则返回null
	 */
	public T findAndModify(Query query, Update update) {
		return mongoTemplate.findAndModify(query, update, this.getEntityClass());
	}
	
	/**
	 * @Title 查询所有
	 * @author 辛加涛
	 * @create 2016年12月5日 下午2:38:46
	 * @return 查询结果
	 */
	public List<T> findAll() {
		return mongoTemplate.findAll(this.getEntityClass());
	}
	
	/**
	 * @Title 根据条件查询多条
	 * @author 辛加涛
	 * @create 2016年12月5日 下午2:38:59
	 * @param query
	 * @return 查询结果
	 */
	public List<T> find(Query query) {
		return mongoTemplate.find(query, this.getEntityClass());
	}
	
	/**
	 * @Title 根据_id查询一条
	 * @author 辛加涛
	 * @create 2016年12月5日 下午2:39:20
	 * @param id
	 * @return 查询结果
	 */
	public T findById(String id) {
		return mongoTemplate.findById(id, this.getEntityClass());
	}
	
	/**
	 * @Title 根据条件查询一条
	 * @author 辛加涛
	 * @create 2016年12月5日 下午2:40:49
	 * @param query
	 * @return 查询结果
	 */
	public T findOne(Query query) {
		return mongoTemplate.findOne(query, this.getEntityClass());
	}
	
	/**
	 * <p>Title: 根据查询条件统计一共有多少条数据</p>
	 * @param query
	 * @return 数据总数
	 * @author 辛加涛
	 * @create 2016年12月23日 上午10:41:24
	 */
	public long count(Query query) {
		return mongoTemplate.count(query, this.getEntityClass());
	}
	
	/**
	 * <p>Title: 分页查询数据</p>
	 * @param queryFilter 实体对象，需继承QueryFilter类
	 * @param query 查询条件
	 * @return 分页数据
	 * @author 辛加涛
	 * @create 2016年12月23日 上午10:41:56
	 */
	public List<T> paging(QueryFilter queryFilter, Query query) {
		// 分页参数合法化
		initQueryFilter(queryFilter);
		// 查询总数
		int totalCount = (int) count(query);
		queryFilter.setTotalCount(totalCount);
		int skip = (queryFilter.getPageNumber() - 1) * queryFilter.getPageSize();
		// 查询分页数据
		return this.find(query.skip(skip).limit(queryFilter.getPageSize()));
	}
	
	/**
	 * <p>Title: 初始化页码和每页数量</p>
	 * <p>Description: 该方法主要用于QueryFilter合法化检查</p>
	 * @param queryFilter
	 * @author 辛加涛
	 * @create 2016年12月22日 下午7:31:08
	 */
	private void initQueryFilter(QueryFilter queryFilter) {
		if (queryFilter == null) {
			queryFilter = new QueryFilter();
			queryFilter.setPageNumber(1);
			queryFilter.setPageSize(10);
			return;
		}
		if (queryFilter.getPageNumber() <= 0) {
			queryFilter.setPageNumber(1);
		}
		if (queryFilter.getPageSize() <= 0) {
			queryFilter.setPageSize(10);
		}
	}
}
