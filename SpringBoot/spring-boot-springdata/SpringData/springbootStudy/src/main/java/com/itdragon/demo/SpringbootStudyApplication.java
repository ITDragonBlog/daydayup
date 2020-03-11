package com.itdragon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class SpringbootStudyApplication {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("hello")
	@ResponseBody
	public String helloWorld() {
		return "Hello SpringBoot !";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStudyApplication.class, args);
	}
}
