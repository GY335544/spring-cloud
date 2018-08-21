# 服务消费者(Feign)

> 简介

	Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果
	Feign 采用的是基于接口的注解，Feign 整合了ribbon
	
> 注解使用
	
	定义一个feign接口，通过@FeignClient("服务名")，来指定调用哪个服务
	@FeignClient("cms-provider")
	public interface CmsTestApi {

		@GetMapping("/cms/hello/{name}")
		public String cmsTest(@PathVariable("name") String name);
	}
	
	@EnableFeignClients注解开启Feign的功能
	
	@SpringBootApplication
	@EnableDiscoveryClient
	@EnableFeignClients
	public class FeignApplication {
		public static void main(String[] args) {
			SpringApplication.run(FeignApplication.class, args);
		}
	}

# 断路器(Hystrix)

> "雪崩"效应

	在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（http），在Spring Cloud可以用RestTemplate+Ribbon和Feign来调用。为了保证其高可用，单个服务通常会集群部署。由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的"雪崩"效应
	注意： springcloud与dubbo+zookeeper的本质区别：
		springcloud 服务与服务直接基于http,dubbo+zookeeper服务与服务基于RPC

> 断路器简介
	
	Netflix开源了Hystrix组件，实现了断路器模式，SpringCloud对这一组件进行了整合。 在微服务架构中，一个请求需要调用多个服务是非常常见的。
	较底层的服务如果出现故障，会导致连锁故障。当对特定的服务的调用的不可用达到一个阀值（Hystric 是5秒20次） 断路器将会被打开。
	断路打开后，可用避免连锁故障，fallback方法可以直接返回一个固定值。

> Feign中使用断路器

	Feign是自带断路器的，在D版本的Spring Cloud中，它没有默认打开。需要在配置文件中配置打开它
	feign.hystrix.enabled=true
	需要在FeignClient的接口的注解中加上fallback的指定类，实现接口并且加入ioc容器中

> 代码
	
	@FeignClient(value="cms-provider",fallback=CmsTestHystric.class)
	public interface CmsTestApi {

		@GetMapping("/cms/hello/{name}")
		public String cmsTest(@PathVariable("name") String name);
	}
	
	@Component
	public class CmsTestHystric implements CmsTestApi{
		@Override
		public String cmsTest(String name) {
			return "sorry "+name;
		}
	}
	
	
		