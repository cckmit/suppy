package com.zjjzfy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zjjzfy.interfaces")
@ComponentScan(basePackages = {"com.zjjzfy.*"})
@EnableAsync
@EnableScheduling
public class SupplyMobileWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyMobileWebApplication.class, args);
	}
}

