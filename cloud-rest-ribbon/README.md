# 服务消费者（rest+ribbon)
> ribbon简介
	
	ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon

> RestTemplate使用
	
	首先在代码中加入RestTemplate的配置类
	@Configuration
	public class RestTemplateConfig {

	    @Bean
	    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
	        return new RestTemplate(factory);
	    }
	
	    @Bean
	    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
	        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	        factory.setReadTimeout(5000);//单位为ms
	        factory.setConnectTimeout(5000);//单位为ms
	        return factory;
	    }
	}
	
	需要访问url的类中注入RestTemplate
	@Autowired
	private RestTemplate restTemplate;
	
	使用restTemplate访问restful接口非常的简单粗暴无脑。 (url, requestMap,ResponseBean.class)这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型
	

> 注解的使用

	@LoadBalanced 使用springcloud ribbon客户端负载均衡的时候，可以给RestTemplate bean 加一个@LoadBalanced注解，就能让这个RestTemplate在请求时拥有客户端负载均衡的能力

> 负载均衡
	
	使用RestTemplate+Ribbon去消费服务，ribbon进行了负载均衡，会轮流的调用服务接口
	
# 在ribbon使用断路器

> pom依赖

	<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-hystrix</artifactId>
	</dependency>

> 注解使用

	启动类Application 加@EnableHystrix注解开启Hystrix
	方法上加上@HystrixCommand注解。该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法