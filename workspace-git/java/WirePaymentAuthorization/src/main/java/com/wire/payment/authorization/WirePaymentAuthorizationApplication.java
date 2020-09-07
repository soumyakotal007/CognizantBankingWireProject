package com.wire.payment.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories({"com.wire.payment.authorization.dao"})
@EntityScan("com.wire.payment.entity")
public class WirePaymentAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WirePaymentAuthorizationApplication.class, args);
	}

}
