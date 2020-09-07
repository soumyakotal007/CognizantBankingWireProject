package com.wire.payment.authorization.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Authorization Response")
public class PaymentAuthorizationResponse {
	@ApiModelProperty(notes = "The response code")
	private int responseCode;
	@ApiModelProperty(notes = "The response time")
	private long responseTime;
	@ApiModelProperty(notes = "The client message")
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
