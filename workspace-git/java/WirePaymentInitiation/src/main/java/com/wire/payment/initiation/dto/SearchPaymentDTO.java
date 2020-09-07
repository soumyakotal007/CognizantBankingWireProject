package com.wire.payment.initiation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Search Payment")
public class SearchPaymentDTO {
	@ApiModelProperty(notes = "The transaction id" , required = true )
	private int transactionId;
	
	@ApiModelProperty(notes = "The customer account id" , required = true )
	private long customerAccountId;
	
	@ApiModelProperty(notes = "The transaction status" , required = true )
	private String transactionStatus;
	
	@ApiModelProperty(notes = "The from date" , required = true )
	private String fromDate;
	
	@ApiModelProperty(notes = "The to date" , required = true )
	private String toDate;
	
	@ApiModelProperty(notes = "The Start Page" , required = true )
	private int pageNo;
	
	@ApiModelProperty(notes = "The End Page" , required = true )
	private int pageSize;
	

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public long getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(long customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
	
	

}
