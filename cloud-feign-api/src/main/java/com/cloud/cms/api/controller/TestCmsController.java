package com.cloud.cms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cms.api.CmsTestApi;

@RestController
public class TestCmsController {

	@Autowired
	private CmsTestApi cmsTestApi;
	
	@RequestMapping("/test/{name}")
	public String test(@PathVariable("name") String name) {
		return cmsTestApi.cmsTest(name);
	}
}
