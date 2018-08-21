# eureka-server 微服务服务端

> pom依赖：

	<!-- 主要用于eureka-server 服务  -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka-server</artifactId>
		</dependency>
	<!-- config-server客户端pom依赖配置 相当于config-client  -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>

> 连接config-server
	
	spring.cloud.config.uri: http://127.0.0.1:8080  用于连接config-server，读取config-server中配置文件

> 启动一个服务注册中心

	@EnableEurekaServer

> 常用配置
	
	eureka是一个高可用的组件，没有后端缓存，每一个实例注册之后需要向注册中心发送心跳（因此可以在内存中完成），在默认情况下erureka server也是一个eureka client ,必须要指定一个 server
	eureka.client.registerWithEureka=false #由于目前创建的应用是一个服务注册中心，而不是普通的应用，默认情况下，应用会向注册中心（也是它自己）注册它自己，设置为false表示禁止这种默认行为
	eureka.client.fetchRegistry=false #表示不去检索其他的服务，因为服务注册中心本身的职责就是维护服务实例，它也不需要去检索其他服务

> 访问地址
	
	http://{ip}:{port}/