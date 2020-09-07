package com.wire.payment.initiation.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wire.payment.entity.TPaymentWireDetail;
import com.wire.payment.initiation.dto.PaymentInitiationRequest;
import com.wire.payment.initiation.dto.PaymentInitiationResponse;
import com.wire.payment.initiation.dto.PaymentWireDTO;
import com.wire.payment.initiation.dto.SearchPaymentDTO;
import com.wire.payment.initiation.dto.SearchPaymentReponse;
import com.wire.payment.initiation.service.PaymentInitiationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Payment Initiation")
public class InitiationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(InitiationController.class);

	@Autowired
	PaymentInitiationService service;

	@ApiOperation(value = "This api is responsible for payment Initiation.", response = PaymentInitiationRequest.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Payment wire id created successfully."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/initiation/payment", headers = "PAYMENT-INITIATION-VERSION=v1.0")
	public PaymentInitiationResponse createPaymentWire(@RequestBody PaymentInitiationRequest request) {
		LOGGER.info("Begin createPaymentWire.createPaymentWire()");
		PaymentInitiationResponse response = new PaymentInitiationResponse();
		try {
			TPaymentWireDetail wireDetail = service.createPaymentWire(request);
			response.setResponseCode(HttpStatus.SC_CREATED);
			response.setClientMessage(""+wireDetail.getTransactionId());
			response.setWireData(wireDetail);
			LOGGER.info(
					"The payment wire initiated successfully.Transaction ID - {} ,Customer ID - {} , Customer Bank ID - {},Merchant ID - {} , Merchant Bank ID - {}",
					wireDetail.getTransactionId(), request.getCustomerId(), request.getCustomerBankId(),
					request.getMerchantId(), request.getMerchantBankId());
		} catch (Exception ex) {
			LOGGER.error(
					"Internal Server Error.Exception - {} , Customer ID - {} , Customer Bank ID - {},Merchant ID - {} , Merchant Bank ID - {}",
					ex.getMessage(), request.getCustomerId(), request.getCustomerBankId(), request.getMerchantId(),
					request.getMerchantBankId());
			response.setResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			response.setClientMessage(ex.getMessage());
		}
		LOGGER.info("End createPaymentWire.createPaymentWire()");
		return response;

	}

	@ApiOperation(value = "This api is responsible for incomplete payment list.", response = PaymentWireDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Incomplete payment list successfully executed."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/incomplete/payments", headers = "PAYMENT-INITIATION-VERSION=v1.0")
	public List<PaymentWireDTO> getnoncompletepayment() {
		List<TPaymentWireDetail> paymentWireList = service.getNotCompletePayments();
		return paymentWireList.stream().map(d -> {
			PaymentWireDTO dto = new PaymentWireDTO();
			BeanUtils.copyProperties(d, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ApiOperation(value = "This api is responsible for complete payment list.", response = PaymentWireDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Complete payment list successfully executed."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/complete/payments", headers = "PAYMENT-INITIATION-VERSION=v1.0")
	public List<PaymentWireDTO> getCompletePayment() {
		List<TPaymentWireDetail> paymentWireList = service.getCompletePayments();
		return paymentWireList.stream().map(d -> {
			PaymentWireDTO dto = new PaymentWireDTO();
			BeanUtils.copyProperties(d, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ApiOperation(value = "This api is responsible for search payment list.", response = SearchPaymentReponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Search payment successfully executed."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/search/payment", headers = "PAYMENT-INITIATION-VERSION=v1.0")
	public SearchPaymentReponse getSearchPayments(@RequestBody SearchPaymentDTO searchPaymentDTO) {
		LOGGER.info("Begin getSearchPayments()");
		SearchPaymentReponse paymentResponse = new SearchPaymentReponse();			
		try {
			paymentResponse.setResponseCode(HttpStatus.SC_OK);
			paymentResponse.setResponseMessage("Search payment successfully executed");
			Page<TPaymentWireDetail> getPaymentWirePage = service.searchPayments(searchPaymentDTO);
			paymentResponse.setTotalPage(getPaymentWirePage.getTotalPages());
			paymentResponse.setPageNo(searchPaymentDTO.getPageNo());
			paymentResponse.setPageSize(searchPaymentDTO.getPageSize());
			List<TPaymentWireDetail> paymentWireList = service.searchPayments(searchPaymentDTO).toList();
			LOGGER.info("Search payment list size- " + paymentWireList.size());
			List<PaymentWireDTO> paymentDtoList = paymentWireList.stream().map(d -> {
				PaymentWireDTO dto = new PaymentWireDTO();
				BeanUtils.copyProperties(d, dto);
				return dto;
			}).collect(Collectors.toList());
			if(paymentDtoList == null || paymentDtoList.isEmpty()) {
				paymentResponse.setResponseCode(HttpStatus.SC_NO_CONTENT);
				paymentResponse.setResponseMessage("No Record Found !!!!");
			}
			paymentResponse.setPaymentList(paymentDtoList);
		} catch (Exception ex) {
			paymentResponse.setResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			paymentResponse.setResponseMessage("Error in Search payment request. Exception message - " + ex.getMessage());
			LOGGER.error("Error in Search payment list " + ex.getMessage());
		}
		return paymentResponse;
	}
}
