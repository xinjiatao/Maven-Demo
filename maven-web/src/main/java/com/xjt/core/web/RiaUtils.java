package com.xjt.core.web;

import java.io.DataInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.xjt.core.persistence.QueryFilter;

/**
 * <p>Title: RIA工具类 </p>
 * <p>Description: RIA工具类 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class RiaUtils {
	
	private static Log logger = LogFactory.getLog(RiaUtils.class);

	/**
	 * 回写Json信息到前台
	 * @param text json字符串
	 * @param response
	 * @throws IOException
	 */
	public static void writeJsonText2Page(String text,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(text);
	}

	/**
	 * 回写iframe Json信息到前台
	 * @param text json字符串
	 * @param response
	 * @throws IOException
	 */
	public static void writeUploadJsonText2Page(String text,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print("<textarea>" + text + "</textarea>");
	}
	
	/**
	 * 初始化查询条件的“当前页码”“每页显示记录数”
	 * @param qf
	 * @param request
	 */
    public static void initQueryFilter(QueryFilter qf, HttpServletRequest request){
    	String page = request.getParameter("page");//当前页码
    	String rows = request.getParameter("rows");//每页显示记录数
    	qf.setPageNumber(Integer.parseInt(page==null ? "1" : page));
    	qf.setPageSize(Integer.parseInt(rows==null ? "10" : rows));
	}
    
    /**
     * @Title: getRequestBody2String
     * @Description: 获取post请求体，并转成字符串
     * @param request
     * @return json格式字符串
     */
    public static String getRequestBody2String(HttpServletRequest request) {
    	String str = null;
    	try {
    		DataInputStream in = new DataInputStream(request.getInputStream());
    		int length = request.getContentLength();
    		byte[] buffer = new byte[length];
    		in.readFully(buffer);
    		str = new String(buffer, "UTF-8");
    		in.close();
		} catch (Exception e) {
			logger.error("RiaUtils--getRequestBody2String():" + e);
		}
		logger.info("RiaUtils--getRequestBody2String():" + str);
    	return str;
    }
}
