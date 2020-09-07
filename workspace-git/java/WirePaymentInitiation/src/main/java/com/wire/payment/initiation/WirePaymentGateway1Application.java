package com.wire.payment.initiation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableEurekaClient
@EnableTransactionManagement
@EnableJpaRepositories({"com.wire.payment.initiation.dao"})
@EntityScan("com.wire.payment.entity")
@SpringBootApplication
public class WirePaymentGateway1Application {

	public static void main(String[] args) {
		SpringApplication.run(WirePaymentGateway1Application.class, args);
	}
	@Bean
	WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
			public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("*");
	        }
	    };
	}

}
