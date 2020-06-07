package me.sta.bytetcc;

import org.bytesoft.bytetcc.supports.springcloud.config.SpringCloudSecondaryConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(SpringCloudSecondaryConfiguration.class)
@EnableEurekaClient
@ComponentScan("me.sta")
@MapperScan("me.sta.bytetcc.dao")
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
public class BytetccServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BytetccServerApplication.class, args);
	}
}
