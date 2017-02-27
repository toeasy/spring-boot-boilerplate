package com.xn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.activation.DataSource;


@Configuration
@SpringBootApplication
@EnableTransactionManagement

public class ShowcaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowcaseApplication.class, args);
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(){
		return new MethodValidationPostProcessor();
	}




}
