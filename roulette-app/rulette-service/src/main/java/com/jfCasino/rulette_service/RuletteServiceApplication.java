package com.jfCasino.rulette_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

//TODO remove exclude when db is configured	
@EnableFeignClients	
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuletteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuletteServiceApplication.class, args);
	}

}
