package com.wire.payment.authorization.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Payment Authorization Request")
public class PaymentAuthorizationRequest {
	@ApiModelProperty(notes = "The merchant id" , required = true )
	private int merchantId;
	@ApiModelProperty(notes = "The bank id" , required = true )
	private int bankId;
	@ApiModelProperty(notes = "The account" , required = true )
	private long account;

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}
	
	

}
