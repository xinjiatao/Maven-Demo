package com.xjt.manager.activemq.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * <p>项目名称：Demo</p>
 * <p>模块名称：xxx</p>
 * <p>功能描述：队列消息监听器 队列模式 一对一</p>
 * <p>类名称：com.xjt.manager.activemq.consumer.QueueReceiver2</p>
 * @author 辛加涛
 * @create 2017年7月27日 下午1:54:18
 */
@Component
public class QueueReceiver1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver1接收到消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
