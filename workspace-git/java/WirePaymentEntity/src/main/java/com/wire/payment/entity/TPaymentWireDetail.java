package com.wire.payment.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the t_payment_wire_details database table.
 * 
 */
@Entity
@Table(name="t_payment_wire_details")
@NamedQuery(name="TPaymentWireDetail.findAll", query="SELECT t FROM TPaymentWireDetail t")
public class TPaymentWireDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="wire_id")
	private int transactionId;

	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="merchant_id")
	private int merchantId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate = new Date(System.currentTimeMillis());

	@Column(name="customer_account")
	private long customerAccount;

	@Column(name="customer_bank_id")
	private int customerBankId;

	@Column(name="merchant_account")
	private long merchantAccount;

	@Column(name="merchant_bank_id")
	private int merchantBankId;

	@Column(name="payment_amt")
	private BigDecimal paymentAmt;

	@Temporal(TemporalType.DATE)
	@Column(name="payment_due_date")
	private Date paymentDueDate  = new Date(System.currentTimeMillis());

	@Column(name="payment_status")
	private String paymentStatus="N";

	//bi-directional many-to-one association to TUserDetail
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="customer_id" ,insertable = false, updatable = false)
	private TUserDetail TUserDetail1;

	//bi-directional many-to-one association to TUserDetail
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="merchant_id",insertable = false, updatable = false)
	private TUserDetail TUserDetail2;

	public TPaymentWireDetail() {
	}

	

	public int getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}



	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	public long getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(long customerAccount) {
		this.customerAccount = customerAccount;
	}

	public int getCustomerBankId() {
		return customerBankId;
	}

	public void setCustomerBankId(int customerBankId) {
		this.customerBankId = customerBankId;
	}

	public long getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(long merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public void setMerchantAccount(int merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public int getMerchantBankId() {
		return this.merchantBankId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getPaymentDueDate() {
		return this.paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public TUserDetail getTUserDetail1() {
		return this.TUserDetail1;
	}

	public void setTUserDetail1(TUserDetail TUserDetail1) {
		this.TUserDetail1 = TUserDetail1;
	}

	public TUserDetail getTUserDetail2() {
		return this.TUserDetail2;
	}

	public void setTUserDetail2(TUserDetail TUserDetail2) {
		this.TUserDetail2 = TUserDetail2;
	}

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
	

}