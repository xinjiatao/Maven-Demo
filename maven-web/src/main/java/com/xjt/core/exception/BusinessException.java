package com.xjt.core.exception;
/**
 * <p>Title: 业务异常处理类 </p>
 * <p>Description: 业务异常处理类 </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class BusinessException extends SystemException
{
	
	public BusinessException()
    {
    }
	
    public BusinessException(String messageCode)
    {
        super(messageCode);
    }

    public BusinessException(String messageCode, Throwable cause)
    {
        super(messageCode, cause);
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
    	return this;
    }
    
    public static void main(String[] args) {
    	BusinessException be = new BusinessException("test");
    	System.out.println(be.getMessageCode()+":"+be.getMessage());;
    }
    
}
    
    
    

