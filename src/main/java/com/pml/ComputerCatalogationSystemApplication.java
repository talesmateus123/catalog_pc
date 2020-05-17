package com.pml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ComputerCatalogationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputerCatalogationSystemApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:8100").allowedOrigins("http://localhost:4200");
			}
		};
	}
	
	
}
