package com.wire.payment.completion.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Verification Response")
public class PaymentVerificationResponse {
	@ApiModelProperty(notes = "The response code" , required = true )
	private int responseCode;
	
	@ApiModelProperty(notes = "The response time" , required = true )
	private long responseTime;
	
	@ApiModelProperty(notes = "The client message" , required = true )
	private String clientMessage;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public String getClientMessage() {
		return clientMessage;
	}

	public void setClientMessage(String clientMessage) {
		this.clientMessage = clientMessage;
	}

}
