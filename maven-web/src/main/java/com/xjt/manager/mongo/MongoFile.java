package com.xjt.manager.mongo;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * <p>功能描述：MongoDB GridFS相关操作</p>
 * <p>类名称：com.tsp.core.mongo.MongoFile</p>
 * @author 康建
 * @create 2016年12月8日 下午3:22:28
 */
@Component(value = "mongoFile")
public class MongoFile {

	@Autowired
	private GridFsTemplate gridfsTemplate;
	
	/**
	 * <p>Title: 根据文件在MongoDB中的_id属性查找一个文件</p>
	 * @param objectId MongoDB中的_id属性
	 * @return {文件名, 文件输出流, 内容类型, 文件长度}
	 * @author 康建
	 * @create 2016年12月8日 下午3:33:52
	 */
	public Object[] findOne(String objectId) {
		if (!StringUtils.isEmpty(objectId)) {
			GridFSDBFile gridfs = gridfsTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(objectId)));
			if (null != gridfs) {
				String fileName = gridfs.getFilename();
				String contentType = gridfs.getContentType();
				long length = gridfs.getLength();
				return new Object[] {fileName, gridfs.getInputStream(), contentType, length};
			}
		}
		return null;
	}
	
	/**
	 * <p>Title: 新增一个文件</p>
	 * @param is 文件输入流
	 * @param fileName 文件名
	 * @param contentType 内容类型
	 * @return GridFSFile
	 * @author 康建
	 * @create 2016年12月8日 下午3:38:38
	 */
	public GridFSFile insert(InputStream is, String fileName, String contentType) {
		return gridfsTemplate.store(is, fileName, contentType);
	}
	
	/**
	 * <p>Title: 根据文件在MongoDB中的_id属性删除一个文件</p>
	 * @param objectId MongoDB中的_id属性
	 * @author 康建
	 * @create 2016年12月8日 下午3:41:06
	 */
	public void delete(String objectId) {
		gridfsTemplate.delete(new Query().addCriteria(Criteria.where("_id").is(objectId)));
	}
}
