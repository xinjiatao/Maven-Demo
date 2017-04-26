package com.xjt.manager.login.controller;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.xjt.core.shiro.manager.TokenManager;
import com.xjt.core.shiro.token.ShiroToken;
import com.xjt.manager.login.bo.Home;
import com.xjt.manager.login.service.HomeService;

/**
 * <p>项目名称：Demo</p>
 * <p>模块名称：xxx</p>
 * <p>功能描述：xxx</p>
 * <p>类名称：com.xjt.Home</p>
 * @author 辛加涛
 * @create 2017年4月10日 上午11:26:15
 */
@Controller
@Scope(value="prototype")
@RequestMapping("home")
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	private Log logger = LogFactory.getLog(HomeController.class);
	
	/**
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月10日 下午1:56:43
	 * @return
	 */
	@RequestMapping(value="indexHome",method = RequestMethod.GET)
	public ModelAndView indexHome(Map<String,Object> map,Model model,ModelMap modelMap){
		System.out.println("Home~~");
		map.put("names", Arrays.asList("caoyc","zhh","cjx"));
		model.addAttribute("time", new Date());
		modelMap.addAttribute("city", "Sy");
		modelMap.put("gender", "male");
		return new ModelAndView("home");
	}
	
	/**
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月10日 下午1:56:43
	 * @return
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index(Map<String,Object> map,Model model,ModelMap modelMap){
		return new ModelAndView("/login/login");
	}
	
	/**
	 * @Title  RESTFul风格的 @PathVariable
	 * @author 辛加涛
	 * @create 2017年4月10日 下午3:19:58
	 * @return
	 */
    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getList(@PathVariable("id") Integer id){
        System.out.println("RESTFul Get"+id);
        
    	Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", 200);
		resultMap.put("message", "@PathVariable 操作成功");
        return resultMap;
    }
    
    /**
     * @Title  @RequestParam传参
     * @author 辛加涛
     * @create 2017年4月10日 下午5:28:55
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value="/user/list",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getListRequestParam(@RequestParam String inputStr, 
    		 @RequestParam(value="age",required=false,defaultValue="0") int age,
    		 HttpServletRequest request){
		//    	value：参数名字，即入参的请求参数名字，如username表示请求的参数区中的名字为username的参数的值将传入；
		//    	required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
		//    	defaultValue：默认值，表示如果请求中没有同名参数时的默认值，默认值可以是SpEL表达式，如“#{systemProperties['java.vm.version']}”。
    	// public Map<String,Object> getListRequestParam(@RequestParam String inputStr, HttpServletRequest request){
    	//URL：http://localhost:8080/maven-web/home//user/list.do?inputStr=f
    	
    	//URL:http://localhost:8080/maven-web/home//user/list.do?inputStr=f&age=15
    	logger.info("@RequestParam inputStr:" + inputStr);
    	logger.info("@RequestParam age:" + age);
        
    	Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", 200);
		resultMap.put("message", "@RequestParam 操作成功");
        return resultMap;
    }
    
    /**
     * @Title  表单提交实体
     * @author 辛加涛
     * @create 2017年4月10日 下午3:54:54
     * @return
     */
	@RequestMapping(value="subRegister",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> subRegister(Home entity){
		logger.info("UserName:" + entity.getNickname());
		
		
    	Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", 200);
		resultMap.put("message", "表单提交实体 操作成功");
        return resultMap;
	}
	
	/**
	 * 分页查询Mybatis plus
	 * @param id
	 * @return
	 */
	@RequestMapping(value="selectTableData")
	@ResponseBody
	public Map<String,Object> selectRoleByUserId(HttpServletRequest req, HttpServletResponse resp){
		Home homeBean = new Home();
		//RiaUtils.initQueryFilter(homeBean, req);
		
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageNum", req.getParameter("page"));
        param.put("pageSize", req.getParameter("rows"));
		
		Page<Home> uuser = homeService.getSelectTable(param,homeBean);

    	Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows", uuser.getRecords());
		resultMap.put("totalCount", uuser.getTotal());
		
//		logger.info(uuser.getSize() );
//		logger.info(uuser.getTotal());
//		logger.info(uuser.getPages());
		return resultMap;
	}
	
	/**
	 * Shrio 登录
	 * @Title 
	 * @author 辛加涛
	 * @create 2017年4月20日 下午1:51:30
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="submitLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> shiroLogin(Home entity, Boolean rememberMe, HttpServletRequest req, HttpServletResponse resp){
			Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			ShiroToken token = new ShiroToken(entity.getEmail(), entity.getPswd());
			//是否记住我
			token.setRememberMe(rememberMe);
			SecurityUtils.getSubject().login(token);
			
			resultMap.put("status", 200);
			resultMap.put("message", "登录成功");
			
			/**
			 * 获取当前登录的用户User对象
			 * @return
			 */
			Home tokenUser = (Home)SecurityUtils.getSubject().getPrincipal();
			
			logger.info("获取用户Session，取得User Id" + TokenManager.getUserId());
			
			Subject subject = SecurityUtils.getSubject();  
			
			if(subject.hasRole("888888")) {  
			    logger.info("有权限，会调用doGetAuthorizationInfo方法");
			} else {  
				logger.info("无权限，会调用doGetAuthorizationInfo方法");
			} 
		
		/**
		 * 这里其实可以直接catch Exception，然后抛出 message即可，但是最好还是各种明细catch 好点。。
		 */
		} catch (DisabledAccountException e) {
			resultMap.put("status", 500);
			resultMap.put("message", "帐号已经禁用。");
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "帐号或密码错误");
		}
		
		return resultMap;
	}
}
