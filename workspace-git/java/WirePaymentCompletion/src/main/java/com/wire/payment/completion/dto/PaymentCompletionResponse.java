package com.wire.payment.completion.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Completion Response")
public class PaymentCompletionResponse {
	@ApiModelProperty(notes = "The response code")
	private int responseCode;
	
	@ApiModelProperty(notes = "The response time")
	private long responseTime;
	
	@ApiModelProperty(notes = "The client message")
	private String clientMessage;
	
	@ApiModelProperty(notes = "The payment wire ID")
	private int wireId;
	

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

	public int getWireId() {
		return wireId;
	}

	public void setWireId(int wireId) {
		this.wireId = wireId;
	}

	

}
