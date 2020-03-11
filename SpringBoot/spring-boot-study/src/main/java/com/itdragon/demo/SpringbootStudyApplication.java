package com.itdragon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootStudyApplication {
	
	@RequestMapping("hello")
	public String helloWorld() {
		return "Hello SpringBoot !";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStudyApplication.class, args);
	}
}
