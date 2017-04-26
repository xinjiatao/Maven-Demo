package com.xjt.core.web;

import java.io.DataInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.xjt.core.persistence.QueryFilter;

/**
 * <p>Title: RIA������ </p>
 * <p>Description: RIA������ </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * @update [�޸���] [�޸�ʱ��]
 * @version 1.0
 */
public class RiaUtils {
	
	private static Log logger = LogFactory.getLog(RiaUtils.class);

	/**
	 * ��дJson��Ϣ��ǰ̨
	 * @param text json�ַ���
	 * @param response
	 * @throws IOException
	 */
	public static void writeJsonText2Page(String text,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(text);
	}

	/**
	 * ��дiframe Json��Ϣ��ǰ̨
	 * @param text json�ַ���
	 * @param response
	 * @throws IOException
	 */
	public static void writeUploadJsonText2Page(String text,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print("<textarea>" + text + "</textarea>");
	}
	
	/**
	 * ��ʼ����ѯ�����ġ���ǰҳ�롱��ÿҳ��ʾ��¼����
	 * @param qf
	 * @param request
	 */
    public static void initQueryFilter(QueryFilter qf, HttpServletRequest request){
    	String page = request.getParameter("page");//��ǰҳ��
    	String rows = request.getParameter("rows");//ÿҳ��ʾ��¼��
    	qf.setPageNumber(Integer.parseInt(page==null ? "1" : page));
    	qf.setPageSize(Integer.parseInt(rows==null ? "10" : rows));
	}
    
    /**
     * @Title: getRequestBody2String
     * @Description: ��ȡpost�����壬��ת���ַ���
     * @param request
     * @return json��ʽ�ַ���
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
