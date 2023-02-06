package com.book.microservice.bookticketservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.book.microservice.bookticketservice.exception.GlobalExceptionHandler;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import(GlobalExceptionHandler.class)
@SpringBootApplication
@EnableSwagger2
//@EnableWebMvc
public class BookTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTicketServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	

}
