package com.wire.payment.authorization.controller;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wire.payment.authorization.dto.PaymentAuthorizationRequest;
import com.wire.payment.authorization.dto.PaymentAuthorizationResponse;
import com.wire.payment.authorization.service.PaymentAuthorizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Payment Authorization")
@RestController
public class PaymentAuthorizationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentAuthorizationController.class);

	@Autowired
	PaymentAuthorizationService service;

	@ApiOperation(value = "This api is responsible for payment Authorization of merchant.", response = PaymentAuthorizationResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 406, message = "Payment authorization request nit acceptable"),
			@ApiResponse(code = 200, message = "Payment successfully completed against the wire id"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/authorization/merchant", headers = "PAYMENT-AUTHORIZATION-VERSION=v1.0")
	public PaymentAuthorizationResponse authorizePayment(@RequestBody PaymentAuthorizationRequest request) {
		LOGGER.info("Begin PaymentAuthorizationController.authorizePayment()");
		PaymentAuthorizationResponse response = new PaymentAuthorizationResponse();
		try {
			if (service.authorizeUser(request)) {
				response.setResponseCode(HttpStatus.SC_OK);
				response.setClientMessage("PAYMENT AUTHORIZATION SUCCESS");
				LOGGER.info("The Merchant bank account authorized successfully.Merchant ID - {} , Bank ID - {} , Account ID - {}",
						request.getMerchantId(), request.getBankId() , request.getAccount());
			} else {
				response.setResponseCode(HttpStatus.SC_NOT_ACCEPTABLE);
				response.setClientMessage("PAYMENT AUTHORIZATION FAILED");
				LOGGER.error("The Merchant bank account authorization failed.Merchant ID - {} , Bank ID - {} , , Account ID - {}",
						request.getMerchantId(), request.getBankId(), request.getAccount());
			}
		} catch (Exception ex) {
			response.setResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			response.setClientMessage(ex.getMessage());
			LOGGER.error(
					"Internal Server error. The Merchant bank account authorization failed.Merchant ID - {} , Bank ID - {} ,  Account ID - {}, Exception message - {}",
					request.getMerchantId(), request.getBankId(), request.getAccount(), ex.getMessage());
		}
		LOGGER.info("End PaymentAuthorizationController.authorizePayment()");
		return response;

	}
}
