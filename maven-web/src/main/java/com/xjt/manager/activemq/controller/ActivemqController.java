package com.xjt.manager.activemq.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xjt.manager.activemq.producer.QueueSender;
import com.xjt.manager.activemq.producer.TopicSender;

/**
 * <p>项目名称：Demo</p>
 * <p>模块名称：xxx</p>
 * <p>功能描述：xxx</p>
 * <p>类名称：com.xjt.manager.activemq.ActivemqController</p>
 * @author 辛加涛
 * @create 2017年7月27日 下午1:41:47
 */

@Controller
@RequestMapping("activemq")
public class ActivemqController {
	
	@Resource //按名称进行装配  “queueSender” 这个名称
	QueueSender queueSender;
	@Resource 
	TopicSender topicSender;
	
	@RequestMapping(value="activeMqPage",method = RequestMethod.GET)
	public String activeMqPage(Map<String,Object> map,Model model,ModelMap modelMap){
		return "activemq/activemq";
	}
	
	/**
	 * 发送消息到队列
	 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message")String message){
		String opt="";
		try {
			queueSender.send("test.queue", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
	/**
	 * 发送消息到主题
	 * Topic主题 ：放入一个消息，所有订阅者都会收到 
	 * 这个是主题目的地是一对多的
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message")String message){
		String opt = "";
		try {
			topicSender.send("test.topic", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}

}
