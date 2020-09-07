package com.wire.payment.completion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import feign.Contract;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories({"com.wire.payment.completion.dao"})
@EntityScan("com.wire.payment.entity")
//@EnableFeignClients("com.wire.payment.completion.client")
//@EnableDiscoveryClient
public class WirePaymentCompletionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WirePaymentCompletionApplication.class, args);
	}
     
//	@Bean
//	public Contract feignContract() {
//	    return new feign.Contract.Default();
//	}
}
