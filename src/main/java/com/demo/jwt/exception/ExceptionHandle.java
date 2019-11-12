package com.demo.jwt.exception;


import com.demo.jwt.exception.customexception.JKException;
import com.demo.jwt.exception.message.ReturnMessage;
import com.demo.jwt.exception.message.ReturnMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHandle {
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
	@ExceptionHandler(value = Exception.class)
	//@ResponseBody
	public ReturnMessage<Object> handle(HttpServletResponse response, Exception exception) {
		 response.setCharacterEncoding("utf-8");
		if(exception instanceof JKException) {
			JKException sbexception = (JKException)exception;
			return ReturnMessageUtil.error(sbexception.getCode(), sbexception.getMessage());
		}else {
			logger.error("系统异常 {}",exception);
			return ReturnMessageUtil.error(-1, "未知异常"+exception.getMessage());
		}
	}
}
