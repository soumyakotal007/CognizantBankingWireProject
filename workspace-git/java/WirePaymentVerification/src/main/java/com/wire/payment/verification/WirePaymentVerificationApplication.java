package com.wire.payment.verification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.wire.payment.verification.dao"})
@EntityScan("com.wire.payment.entity")
@EnableEurekaClient
public class WirePaymentVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WirePaymentVerificationApplication.class, args);
	}

}
