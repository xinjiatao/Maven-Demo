package com.xjt.manager.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xjt.core.base.BaseService;
import com.xjt.manager.login.bo.Home;
import com.xjt.manager.login.dao.HomeMapper;

/**
 * <p>项目名称：Demo</p>
 * <p>模块名称：xxx</p>
 * <p>功能描述：xxx</p>
 * <p>类名称：login.HomeServiceImpl</p>
 * @author 辛加涛
 * @create 2017年4月11日 上午11:04:40
 */
@Service
public class HomeServiceImpl extends BaseService implements HomeService{
	
	private Log logger = LogFactory.getLog(HomeServiceImpl.class);
	 
	@Autowired
	HomeMapper homeMapper;
	
	/**
	 * 动态代理查询
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月11日 上午11:04:34
	 * @return
	 */
	public List<Home> findUser2() {
		return homeMapper.findUser2();
	}

	/**
	 * 分页查询
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月18日 下午2:00:23
	 * @param req
	 * @param resp
	 * @return
	 */
	public Page<Home> getSelectTable(Map<String, Object> params,Home user) {
		
		Page<Home> p = this.getPage(Home.class, (Map<String, Object>) params);
		//第一个参数 返回 类型， 第二个参数 sql 参数
		List<Home> userList =homeMapper.selectUserPage(p, params);
		logger.info("Size : " + userList.size());
		
        p.setRecords(userList);
        return p;
	}

	public Home login(String email, String pswd) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("pswd", pswd);
		Home user = homeMapper.login(map);
		return user;
	}

	public Set<String> findRoleByUserId(Long userId) {
		return homeMapper.findRoleByUserId(userId);
	}

	public Set<String> findPermissionByUserId(Long userId) {
		return homeMapper.findPermissionByUserId(userId);
	}

}
