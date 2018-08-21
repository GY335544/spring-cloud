package com.cloud.cms.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cloud.cms.api.hystric.CmsTestHystric;

@FeignClient(value="cms-provider",fallback=CmsTestHystric.class)
public interface CmsTestApi {

	@GetMapping("/cms/hello/{name}")
	public String cmsTest(@PathVariable("name") String name);
}
