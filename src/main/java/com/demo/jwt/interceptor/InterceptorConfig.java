package com.demo.jwt.interceptor;

import com.demo.jwt.utils.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private JWTService jwtService;
	@Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor(jwtService);
    }
	 @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/logout").excludePathPatterns("/loginSys").excludePathPatterns("/static/**");
    }
}
