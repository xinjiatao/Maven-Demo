package com.xjt.core.exception;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Title: exception_message_i18n属性文件读取类 </p>
 * <p>Description: exception_message_i18n属性文件读取类 </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class MessageUtil {
	
	private static Log log = LogFactory.getLog(MessageUtil.class);
    private static PropertyResourceBundle prb = (PropertyResourceBundle)ResourceBundle.getBundle("exception_message_i18n");

    private MessageUtil() {

    }

    public static final String getString(String propertyName) {
    	String message = null;
        try {
        	message = prb.getString(propertyName);   
        }
        catch (Exception e) {
        	log.warn(("娌℃湁鎵惧埌寮傚父缂栫爜[" + propertyName + "]瀵瑰簲鐨勫紓甯搁厤缃俊鎭紒"));
        }
        if (StringUtils.isEmpty(message)) {
            return propertyName;
        }
        return message;
    }
    
    public static void main(String[] args) {
    	System.out.println(MessageUtil.getString("test"));
    }
}
