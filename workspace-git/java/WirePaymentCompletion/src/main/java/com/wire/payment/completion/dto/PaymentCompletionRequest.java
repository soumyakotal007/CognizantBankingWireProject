package com.wire.payment.completion.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Completion Request")
public class PaymentCompletionRequest {
	@ApiModelProperty(notes = "The wire id" , required = true )
	private int wireID;
	@ApiModelProperty(notes = "The payment amount" , required = true )
	private BigDecimal paymentAmount;
	public int getWireID() {
		return wireID;
	}
	public void setWireID(int wireID) {
		this.wireID = wireID;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	

	

}
