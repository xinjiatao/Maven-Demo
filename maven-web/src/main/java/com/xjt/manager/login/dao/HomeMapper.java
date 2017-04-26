package com.xjt.manager.login.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.plugins.Page;
import com.xjt.manager.login.bo.Home;

/**
 * <p>项目名称：Demo</p>
 * <p>模块名称：xxx</p>
 * <p>功能描述：xxx</p>
 * <p>类名称：login.HomeDao</p>
 * @author 辛加涛
 * @create 2017年4月11日 上午11:05:59
 */
public interface HomeMapper {

	public List<Home> findUser2();
	
	/**
	 * baomidou
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月18日 下午5:34:58
	 * @param req
	 * @param resp
	 * @return
	 */
	public List<Home> selectUserPage(Page<Home> page,Map<String, Object> params);
	
	/**
	 * Shiro 登录
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月20日 下午2:24:25
	 * @param req
	 * @param resp
	 * @return
	 */
	Home login(Map<String, Object> map);
	
	/**
	 * 根据用户ID查询角色（role），放入到Authorization里。
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月20日 下午3:10:03
	 * @param req
	 * @param resp
	 * @return
	 */
	Set<String> findRoleByUserId(Long id);
	
	/**
	 * 根据用户ID获取权限的Set集合
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月20日 下午3:16:08
	 * @param req
	 * @param resp
	 * @return
	 */
	Set<String> findPermissionByUserId(Long id);
}
