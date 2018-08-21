# config-server 服务端配置中心

> 简介
	
	在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，需要分布式配置中心组件。在Spring Cloud中，有分布式配置中心组件spring cloud config ，它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中。在spring cloud config 组件中，分两个角色，一是config server，二是config client
	
> 构建Config Server

	<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
     </dependency>
	
	程序的入口Application类加上@EnableConfigServer注解开启配置服务器的功能

	远程配置：
		spring.cloud.config.server.git.uri：配置git仓库地址
		spring.cloud.config.server.git.searchPaths：配置仓库路径
		spring.cloud.config.label：配置仓库的分支
		spring.cloud.config.server.git.username：访问git仓库的用户名
		spring.cloud.config.server.git.password：访问git仓库的用户密码
		如果Git仓库为公开仓库，可以不填写用户名和密码，如果是私有仓库需要填写
	
	
> 项目启动访问方式:
	
	远程git：
		/{application}/{profile}[/{label}]
		/{application}-{profile}.yml
		/{label}/{application}-{profile}.yml
		/{application}-{profile}.properties
		/{label}/{application}-{profile}.properties
		上面的url会映射{application}-{profile}.properties对应的配置文件，{label}对应git上不同的分支，默认为master
		http://localhost:8080/application-dev.yml访问上面的application-dev.yml文件	
	本地native：
		http://localhost:8080/eureka-server.yml

> config-server 注册到eureka-server中
	
	添加pom依赖:
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
		</dependency>
	添加注册中心:
		#注册中心
			eureka:
			  client:
			    serviceUrl:
			      defaultZone: http://127.0.0.1:8081/eureka

> 常用配置选项

	eureka.client.service-url.defaultZone 设置了注册中心的地址，将服务注册到eureka注册中心中去 
	spring.cloud.config.discovery.enabled 表示开启通过服务名来访问config-server
	spring.cloud.config.discovery.service-id=config-server 则表示config-server的服务名

> 构建config client
	
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
	 
	spring.cloud.config.label 指明远程仓库的分支
	spring.cloud.config.profile
		dev:开发环境配置文件
		test:测试环境
		pro:正式环境
	spring.cloud.config.uri= http://{ip}:{port}/ 指明配置服务中心的地址