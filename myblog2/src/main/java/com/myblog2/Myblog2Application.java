package com.myblog2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Myblog2Application {

	public static void main(String[] args) {
		SpringApplication.run(Myblog2Application.class, args);
	}
	@Bean
	public ModelMapper getModelMapper(){

		return new ModelMapper();
	}


}
