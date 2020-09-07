package com.wire.payment.completion.controller;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.wire.payment.completion.dto.PaymentCompletionRequest;
import com.wire.payment.completion.dto.PaymentCompletionResponse;
import com.wire.payment.completion.dto.PaymentUpdateRequest;
import com.wire.payment.completion.service.PaymentCompletionService;
import com.wire.payment.entity.TPaymentWireDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Payment Completion")
public class PaymentCompletionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentCompletionController.class);
	
	@Autowired
	PaymentCompletionService service;
	
	
     
	@ApiOperation(value = "This api is responsible for payment complition", response = PaymentCompletionResponse.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 202, message = "Payment successfully completed against the wire id"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Internal server error")
	}
	)
	 @PostMapping(value="/completion/payment",headers = "PAYMENT-COMPLETION-VERSION=v1.0")
	 public PaymentCompletionResponse paymentCompletion(@RequestHeader HttpHeaders httpHeaders,@RequestBody PaymentCompletionRequest request) {
		LOGGER.info("Begin PaymentCompletionController.paymentCompletion()");
		 PaymentCompletionResponse response = new PaymentCompletionResponse();
		 Map<String,String> headerMap=httpHeaders.toSingleValueMap();
		 try {
			 response.setResponseCode(HttpStatus.SC_OK);
			 
			 TPaymentWireDetail wireDtls = service.completePayment(request , headerMap);
			 if(wireDtls.getPaymentStatus().equalsIgnoreCase("Y")) {
				 LOGGER.info("Payment is completed.wire ID - {}",request.getWireID());
				 response.setClientMessage("PAYMENT DONE TO MARCHANT. Paymwwent ID - " + request.getWireID());
				 response.setWireId(wireDtls.getTransactionId());
			 }
		 }
		 catch(Exception ex) {
			 ex.printStackTrace();
			 LOGGER.error("Internal Server error in Payment Completion.Exception message - {} , wire ID - {}",ex.getMessage(), request.getWireID());
			 response.setResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			 response.setClientMessage(ex.getMessage());
		 }
		 LOGGER.info("End PaymentCompletionController.paymentCompletion()");
		 return response;
	 }
	
	@ApiOperation(value = "This api is responsible for updating payment details", response = PaymentCompletionResponse.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Payment successfully updated against the transaction id"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	        @ApiResponse(code = 500, message = "Internal server error")
	}
	)
	 @PostMapping(value="/update/payment",headers = "PAYMENT-COMPLETION-VERSION=v1.0")
	 public PaymentCompletionResponse updatePayment(@RequestHeader HttpHeaders httpHeaders,@RequestBody PaymentUpdateRequest request) {
		LOGGER.info("Begin PaymentCompletionController.updatePayment()");
		 PaymentCompletionResponse response = new PaymentCompletionResponse();
		 Map<String,String> headerMap=httpHeaders.toSingleValueMap();
		 try {
			 response.setResponseCode(HttpStatus.SC_OK);
			 
			 TPaymentWireDetail wireDtls = service.updatePayment(request);
			 LOGGER.info("Payment details is updated .Payment ID - {}",request.getTransactionId());
			 response.setClientMessage("PAYMENT details is updated. Transaction ID - " + request.getTransactionId());
			 response.setWireId(wireDtls.getTransactionId());
		 }
		 catch(Exception ex) {
			 ex.printStackTrace();
			 LOGGER.error("Internal Server error in Payment update.Exception message - {} , wire ID - {}",ex.getMessage(), request.getTransactionId());
			 response.setResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			 response.setClientMessage(ex.getMessage());
		 }
		 LOGGER.info("End PaymentCompletionController.updatePayment()");
		 return response;
	 }
}
