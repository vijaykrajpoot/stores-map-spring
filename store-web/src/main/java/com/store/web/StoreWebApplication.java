package com.store.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "com.store.web", "com.store.service" })
//@ComponentScan({"com.store.web","com.store.service"})
//@EnableAutoConfiguration
//@Configuration
//@EnableWebMvc
public class StoreWebApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StoreWebApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(StoreWebApplication.class, args);
	}

}
