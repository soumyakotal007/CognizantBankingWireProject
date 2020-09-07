package com.wire.payment.completion.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PaymentUpdateRequest {
	private long customerAccount;
	private long merchantAccount;
	private String paymentDueDate;
	private BigDecimal paymentAmt;
    private int transactionId;
    private int customerId;
    private int customerBankId;
    private int merchantId;
    private int merchantBankId;
    private String paymentStatus;
	
	
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
	
	public String getPaymentDueDate() {
		return paymentDueDate;
	}
	public void setPaymentDueDate(String paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}
	public BigDecimal getPaymentAmt() {
		return paymentAmt;
	}
	public void setPaymentAmt(BigDecimal paymentAmt) {
		this.paymentAmt = paymentAmt;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getCustomerBankId() {
		return customerBankId;
	}
	public void setCustomerBankId(int customerBankId) {
		this.customerBankId = customerBankId;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public int getMerchantBankId() {
		return merchantBankId;
	}
	public void setMerchantBankId(int merchantBankId) {
		this.merchantBankId = merchantBankId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
    
    
}
