package com.wire.payment.verification.controller;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wire.payment.verification.dto.PaymentVerificationRequest;
import com.wire.payment.verification.dto.PaymentVerificationResponse;
import com.wire.payment.verification.service.UserRepositoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Payment Verification")
@RestController
public class PaymentVerificationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentVerificationController.class);
	
	@Autowired
	UserRepositoryService service;

	@ApiOperation(value = "This api is responsible for payment Verification of merchant.", response = PaymentVerificationRequest.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Payment verified successfully."),
	        @ApiResponse(code = 406, message = "Payment not acceptable."),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Internal server error")
	}) 
	@PostMapping(value="/verification/customer",headers = "PAYMENT-VERIFICATION-VERSION=v1.0")
	public PaymentVerificationResponse paymentVerification(@RequestBody PaymentVerificationRequest request) {
		LOGGER.info("Begin PaymentVerificationController.paymentVerification()");
		PaymentVerificationResponse response = new PaymentVerificationResponse();
		try {
			if(service.verifyUser(request)) {
				LOGGER.info("The customer bank account verified successfully.Customer ID - {} , Bank ID - {} ,  Account ID {}" , request.getCustomerId() , request.getBankId() , request.getAccount());
				response.setResponseCode(HttpStatus.SC_OK);
				response.setClientMessage("PAYMENT VERIFICATION SUCCESS");
			}
			else {
				LOGGER.error("The customer bank account verification failed.Customer ID - {} , Bank ID - {} , Account ID {}" , request.getCustomerId() , request.getBankId() , request.getAccount());
				response.setResponseCode(HttpStatus.SC_NOT_ACCEPTABLE);
				response.setClientMessage("PAYMENT VERIFICATION FAILED");
			}
		}
		catch(Exception ex) {
			LOGGER.error("Internal Server error. customer bank account verification failed.Customer ID - {} , Bank ID - {} ,  Account ID {} , Exception message - {}" , request.getCustomerId() , request.getBankId() , request.getAccount() , ex.getMessage());
			response.setResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			response.setClientMessage(ex.getMessage());
		}
		LOGGER.info("End PaymentVerificationController.paymentVerification()");
		return response;
	}
}
