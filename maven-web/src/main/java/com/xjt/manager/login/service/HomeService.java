package com.xjt.manager.login.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.plugins.Page;
import com.xjt.manager.login.bo.Home;

public interface HomeService {

	public List<Home> findUser2();
	
	public Page<Home> getSelectTable(Map<String, Object> params,Home user) ;
	
	//Shiro 用户登录
	Home login(String email ,String pswd);
	
	//根据用户ID查询角色（role），放入到Authorization里。
	Set<String> findRoleByUserId(Long userId);
	
	//根据用户ID查询权限（permission），放入到Authorization里。
	Set<String> findPermissionByUserId(Long userId);
}
