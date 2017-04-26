package com.xjt.core.exception;


/**
 * <p>Title: 系统异常处理类 </p>
 * <p>Description: 系统异常处理类 </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class SystemException extends RuntimeException{
	
	private String messageCode;
	
	public SystemException()
    {
    }
	
    public SystemException(String messageCode)
    {
        super(MessageUtil.getString(messageCode));
        this.messageCode = messageCode == null ? "" : messageCode;
    }

    public SystemException(String messageCode, Throwable cause)
    {
        super(MessageUtil.getString(messageCode), cause);
        this.messageCode = messageCode == null ? "" : messageCode;
    }

    public SystemException(Throwable cause)
    {
        super(cause);
        this.messageCode = "";
    }
    
    public String getMessageCode() {
		return messageCode;
	}
    
    public static void main(String[] args) {
    	SystemException be = new SystemException("test");
    	System.out.println(be.getMessageCode()+":"+be.getMessage());;
    	
//    	try {
//			int i = 0;
//			int j = 5;
//			double s = j/i;
//		} catch (Exception e) {
//			throw new SystemException(e);
//		}
    	
    }
    
}
