package com.demo.jwt.controller;


import com.demo.jwt.exception.message.ReturnMessage;
import com.demo.jwt.exception.message.ReturnMessageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@GetMapping("index")
	public ReturnMessage index() {
		return ReturnMessageUtil.sucess();
	}
}
