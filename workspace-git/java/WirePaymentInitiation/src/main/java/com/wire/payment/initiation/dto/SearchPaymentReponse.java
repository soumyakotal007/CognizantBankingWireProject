package com.wire.payment.initiation.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SearchPaymentReponse {
	@ApiModelProperty(notes = "The response code" , required = true )
	private int responseCode;
	@ApiModelProperty(notes = "The response message" , required = true )
	private String responseMessage;
	@ApiModelProperty(notes = "The payment list" , required = true )
	private List<PaymentWireDTO> paymentList;
	@ApiModelProperty(notes = "The Start Page" , required = true )
	private int pageNo;
	@ApiModelProperty(notes = "The page Size" , required = true )
	private int pageSize;
	@ApiModelProperty(notes = "The Total Page" , required = true )
	private int totalPage;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public List<PaymentWireDTO> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentWireDTO> paymentList) {
		this.paymentList = paymentList;
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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
