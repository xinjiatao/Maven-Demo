package com.xjt.core.shiro.realm;

import java.util.Date;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xjt.core.shiro.manager.TokenManager;
import com.xjt.core.shiro.token.ShiroToken;
import com.xjt.manager.login.bo.Home;
import com.xjt.manager.login.service.HomeService;

public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	HomeService homeService;
	
	public MyRealm() {
		super();
	}
	
	/**
	 * 认证信息，主要针对用户登录，用于登录验证 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		//用于登录验证 
		System.out.println("doGetAuthenticationInfo");
		ShiroToken token = (ShiroToken) authcToken;
		Home user = homeService.login(token.getUsername(),token.getPswd());
		if(null == user){
			throw new AccountException("帐号或密码不正确！");
		/**
		 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
		 */
		}else if(Home._0.equals(user.getStatus())){
			throw new DisabledAccountException("帐号已经禁止登录！");
		}else{
			//更新登录时间 last login time
			user.setLastLoginTime(new Date());
			//homeService.updateByPrimaryKeySelective(user);
		}
		return new SimpleAuthenticationInfo(user,user.getPswd(), getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//    	Long userId = TokenManager.getUserId();
//		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
//		//����û�ID��ѯ��ɫ��role�������뵽Authorization�
//		Set<String> roles = roleService.findRoleByUserId(userId);
//		info.setRoles(roles);
//		//����û�ID��ѯȨ�ޣ�permission�������뵽Authorization�
//		Set<String> permissions = permissionService.findPermissionByUserId(userId);
//		info.setStringPermissions(permissions);
//        return info;
		
		//ʲôʱ�����÷���
//		1��subject.hasRole(��admin��) �� subject.isPermitted(��admin��)���Լ�ȥ��������Ƿ���ʲô��ɫ�����Ƿ���ʲôȨ�޵�ʱ��
//		2��@RequiresRoles("admin") ���ڷ����ϼ�ע���ʱ��
//		3��[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]����ҳ���ϼ�shiro��ǩ��ʱ�򣬼������ҳ���ʱ��ɨ�赽�������ǩ��ʱ��
        System.out.println("doGetAuthorizationInfo");
        Long userId = TokenManager.getUserId();
		//Long userId = (Long) principalCollection.getPrimaryPrincipal() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
		//����û�ID��ѯ��ɫ��role�������뵽Authorization�
		Set<String> roles = homeService.findRoleByUserId(userId);
		info.setRoles(roles);
		//����û�ID��ѯȨ�ޣ�permission�������뵽Authorization�
		Set<String> permissions = homeService.findPermissionByUserId(userId);
		info.setStringPermissions(permissions);
        return info;
	}
	
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

}
