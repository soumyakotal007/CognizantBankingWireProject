package com.wire.payment.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wire.payment.gateway.filter.ErrorFilter;
import com.wire.payment.gateway.filter.PostFilter;
import com.wire.payment.gateway.filter.PreFilter;
import com.wire.payment.gateway.filter.RouteFilter;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class WirePaymentGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WirePaymentGatewayApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
	
//	@Bean
//	WebMvcConfigurer corsConfigurer() {
//	    return new WebMvcConfigurer() {
//	        @Override
//			public void addCorsMappings(CorsRegistry registry) {
//	            registry.addMapping("/**");
//	        }
//	    };
//	}

}
