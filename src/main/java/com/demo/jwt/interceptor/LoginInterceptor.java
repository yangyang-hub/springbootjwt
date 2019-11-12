package com.demo.jwt.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.demo.jwt.exception.customexception.JKException;
import com.demo.jwt.exception.message.CodeEnum;
import com.demo.jwt.utils.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class LoginInterceptor implements HandlerInterceptor{
	
	Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	private JWTService jwtService;
	
	public LoginInterceptor(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		
		log.info("Token Checkout processing");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

//		String token = request.getParameter("token");


		String token = null;
		try {
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null) {
				responseStrBuilder.append(inputStr);
			}
			JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
			token = jsonObject.getString("token");
			if (token.startsWith("[")){
				token = token.replace("[", "").replace("]", "").replace("\"","");
			}
//            System.out.println(token);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (StringUtils.isEmpty(token)) {
			throw new JKException(CodeEnum.TOKENISEMPTY);
		}

		if (StringUtils.isEmpty(token)) {
			throw new JKException(CodeEnum.TOKENISEMPTY);
		}
		
//		String tokenInServletContext = (String)request.getServletContext().getAttribute(token);
//		if(StringUtils.isEmpty(tokenInServletContext)) {
//			throw new JKException(CodeEnum.ILLEGALTOKEN);
//		}
		
		try {
			 jwtService.verifyToken(token);
		} catch (AlgorithmMismatchException  e) {
			log.error("Token Checkout processing AlgorithmMismatchException 异常！"+e.getLocalizedMessage());
			throw new JKException(CodeEnum.ILLEGALTOKEN);
		}catch (TokenExpiredException  e) {
			log.info("token已经过期");
			throw new JKException(CodeEnum.EXPIRETOKEN);
		}catch (SignatureVerificationException  e) {
			log.error("Token Checkout processing SignatureVerificationException 异常！"+e.getLocalizedMessage());
			throw new JKException(CodeEnum.ILLEGALTOKEN);
		 }catch (Exception e) {
			log.error("Token Checkout processing 未知异常！"+e.getLocalizedMessage());
			throw e;
		}
		
		return true;
	}

	@Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
