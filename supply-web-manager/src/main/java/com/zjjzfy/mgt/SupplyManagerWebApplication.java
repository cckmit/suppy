package com.zjjzfy.mgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zjjzfy.interfaces")
//@org.mybatis.spring.annotation.MapperScan("com.zjjzfy.shiro.mapper")
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {"com.zjjzfy.*"})
@ImportResource(locations={"classpath:mykaptcha.xml"})
public class SupplyManagerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyManagerWebApplication.class, args);
	}
}
