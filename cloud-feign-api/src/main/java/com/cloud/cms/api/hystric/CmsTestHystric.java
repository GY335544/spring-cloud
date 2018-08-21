package com.cloud.cms.api.hystric;

import org.springframework.stereotype.Component;

import com.cloud.cms.api.CmsTestApi;

@Component
public class CmsTestHystric implements CmsTestApi{

	@Override
	public String cmsTest(String name) {
		return "sorry "+name;
	}

}
