package com.cloud.cms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms")
public class TestController {

	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/hello/{name}")
	public String test(@PathVariable("name") String name) {
		return "hello "+port+" "+name;
	}
}
