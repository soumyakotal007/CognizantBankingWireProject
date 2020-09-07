package com.wire.payment.completion.client;

import org.springframework.context.annotation.Bean;

import feign.Contract;
import feign.Request;

public class FeignConfiguration {
	@Bean 
	public Request.Options timeoutConfiguration(){
	  return new Request.Options(5000, 30000);
	}
}
