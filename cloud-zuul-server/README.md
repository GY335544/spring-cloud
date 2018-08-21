# 路由网关(zuul)

> zuul简介

	Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/user转发到到user服务，/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能
	
> POM依赖

	<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zuul</artifactId>
	</dependency>

> 注解

	@EnableZuulProxy 开启zuul功能
	
> 配置
	
	zuul:
	  routes:
	    api-a:
	      path: /api-a/**					
	      serviceId: service-ribbon	 			#http://localhost:9400/api-a/cms/consumer/zuul		
	    api-b:
	      path: /api-b/**
	      serviceId: service-feign				#http://localhost:9400/api-b/test/zuul
	/api-a/ 开头的请求都转发给service-ribbon服务；以/api-b/开头的请求都转发给service-feign服务
	
> 服务过滤
	
	zuul不仅只是路由，并且还能过滤，做一些安全验证:
		@Component
		public class MyFilter extends ZuulFilter{
			...
		}
		
		filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： 
		pre：路由之前
		routing：路由之时
		post： 路由之后
		error：发送错误调用
		filterOrder：过滤的顺序
		shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
		run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
		
	  请求：http://localhost:9400/api-a/cms/consumer/zuul?token=a
	