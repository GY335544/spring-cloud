package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ServiceRibbonController {

	private static final String REST_URL_PREFIX = "http://CMS-PROVIDER";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/cms/consumer/{str}")
	@HystrixCommand(fallbackMethod = "dealError")
	public String getCmsProviderTest(@PathVariable("str") String str) {
		return restTemplate.getForObject(REST_URL_PREFIX+"/cms/hello/"+str, String.class);
	}
	
	public String dealError(@PathVariable("str") String str) {
		 return "request error...";
	}
}
