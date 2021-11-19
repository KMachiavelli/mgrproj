package com.example.mgrbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class MgrBackendApplication {

	@Configuration
	@EnableWebMvc
	public class WebConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("*");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(MgrBackendApplication.class, args);
	}

}
