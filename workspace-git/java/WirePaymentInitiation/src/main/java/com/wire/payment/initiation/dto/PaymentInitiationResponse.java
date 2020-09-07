package com.wire.payment.initiation.dto;

import com.wire.payment.entity.TPaymentWireDetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Initiation Request")
public class PaymentInitiationResponse {
	@ApiModelProperty(notes = "The response code")
	private int responseCode;
	
	@ApiModelProperty(notes = "The response time")
	private long responseTime;
	
	@ApiModelProperty(notes = "The client message")
	private String clientMessage;
	
	@ApiModelProperty(notes = "The payment wire detail")
	private TPaymentWireDetail wireData;

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

	public TPaymentWireDetail getWireData() {
		return wireData;
	}

	public void setWireData(TPaymentWireDetail wireData) {
		this.wireData = wireData;
	}

}
