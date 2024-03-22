package com.prashantbarge.learning2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Learning2Application {

	public static void main(String[] args) {
		SpringApplication.run(Learning2Application.class, args);
	}

}
