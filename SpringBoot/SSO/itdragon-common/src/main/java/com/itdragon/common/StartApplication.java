package com.itdragon.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 打包需要加上该类，否则提示：
 * Execution default of goal org.springframework.boot:spring-boot-maven-plugin:1.5.9.RELEASE:repackage failed: Unable to find main class
 */
@SpringBootApplication
public class StartApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

}
