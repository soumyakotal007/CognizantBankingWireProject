package com.wire.payment.initiation.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Initiation Request")
public class PaymentInitiationRequest {
	@ApiModelProperty(notes = "The customer id" , required = true )
	private int customerId;
	
	@ApiModelProperty(notes = "The merchant id" , required = true )
	private int merchantId;
	
	@ApiModelProperty(notes = "The customer bank id" , required = true )
	private int customerBankId;
	
	@ApiModelProperty(notes = "The merchant bank id" , required = true )
	private int merchantBankId;
	
	@ApiModelProperty(notes = "The payment ammount" , required = true )
	private BigDecimal paymentAmt;
	
	@ApiModelProperty(notes = "The customer account" , required = true )
	private long customerAccount;
	
	@ApiModelProperty(notes = "The Merchant Account" , required = true )
	private long merchantAccount;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getCustomerBankId() {
		return customerBankId;
	}

	public void setCustomerBankId(int customerBankId) {
		this.customerBankId = customerBankId;
	}

	public int getMerchantBankId() {
		return merchantBankId;
	}

	public void setMerchantBankId(int merchantBankId) {
		this.merchantBankId = merchantBankId;
	}

	public BigDecimal getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(BigDecimal paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public long getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(long customerAccount) {
		this.customerAccount = customerAccount;
	}

	public long getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(long merchantAccount) {
		this.merchantAccount = merchantAccount;
	}
}
