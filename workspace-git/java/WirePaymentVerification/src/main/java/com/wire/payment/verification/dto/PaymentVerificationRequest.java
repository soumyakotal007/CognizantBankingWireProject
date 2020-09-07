package com.wire.payment.verification.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Verification Request")
public class PaymentVerificationRequest {
	@ApiModelProperty(notes = "The customer id" , required = true )
	private int customerId;
	
	@ApiModelProperty(notes = "The bank id" , required = true )
	private int bankId;
	
	@ApiModelProperty(notes = "The payment amount" , required = true )
	private BigDecimal paymentAmount;

	@ApiModelProperty(notes = "The bank account" , required = true )
	private long account;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}
}
