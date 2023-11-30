package com.springboot.app;

import com.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.controller", "com.service", "com.student"})
@EntityScan(basePackages = {"com.student"})
public class SpringBootWorkshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWorkshopApplication.class, args);
	}

}
