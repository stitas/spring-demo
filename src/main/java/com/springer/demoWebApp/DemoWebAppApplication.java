package com.springer.demoWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebAppApplication.class, args);
	}

}
