package com.wire.payment.completion.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.wire.payment.completion.dto.PaymentVerificationRequest;
import com.wire.payment.completion.dto.PaymentVerificationResponse;

import feign.HeaderMap;
import feign.RequestLine;

@FeignClient(name = "payment-verification", configuration = FeignConfiguration.class)
//@RibbonClient(name = "payment-verification", configuration = RibbonConfiguration.class)
public interface PaymentVerificationClient {

//	@Headers({ "Authorization: {basicAuth}", "PAYMENT-VERIFICATION-VERSION: {version}" })
	//@PostMapping("/verification/customer")
	@RequestLine("POST /verification/customer")
	//@RequestMapping(method = RequestMethod.POST,  value = "/verification/customer",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PaymentVerificationResponse paymentVerification(@HeaderMap  Map<String,String> map, @RequestBody PaymentVerificationRequest request);
}
