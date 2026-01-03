package com.udemy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // Habilita o uso do OpenFeign para comunicação entre serviços (apis externas)
@ConfigurationPropertiesScan // Habilita o scan de classes anotadas com @ConfigurationProperties
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
