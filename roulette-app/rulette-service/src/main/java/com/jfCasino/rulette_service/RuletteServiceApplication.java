package com.jfCasino.rulette_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//TODO remove exclude when db is configured		
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuletteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuletteServiceApplication.class, args);
	}

}
