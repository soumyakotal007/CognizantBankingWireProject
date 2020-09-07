package com.wire.payment.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the t_user_bank_details database table.
 * 
 */
@Entity
@Table(name="t_user_bank_details")
@NamedQuery(name="TUserBankDetail.findAll", query="SELECT t FROM TUserBankDetail t")
public class TUserBankDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="record_id")
	private int recordId;

	@Column(name="bank_acc_id")
	private long bankAccId;

	@Column(name="bank_balance")
	private BigDecimal bankBalance;
	
	@Column(name="bank_id")
	private int bankID;
	
	@Column(name="user_id")
	private int userId;
	

	//bi-directional many-to-one association to TBank
	@ManyToOne
	@JoinColumn(name="bank_id",insertable = false, updatable = false)
	private TBank TBank;

	//bi-directional many-to-one association to TUserDetail
	@ManyToOne
	@JoinColumn(name="user_id",insertable = false, updatable = false)
	private TUserDetail TUserDetail;

	//bi-directional many-to-one association to TUserType
	@ManyToOne
	@JoinColumn(name="user_type_id",insertable = false, updatable = false)
	private TUserType TUserType;

	public TUserBankDetail() {
	}

	public int getRecordId() {
		return this.recordId;
	}

	public long getBankAccId() {
		return bankAccId;
	}

	public void setBankAccId(long bankAccId) {
		this.bankAccId = bankAccId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public void setBankAccId(int bankAccId) {
		this.bankAccId = bankAccId;
	}

	

	public BigDecimal getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(BigDecimal bankBalance) {
		this.bankBalance = bankBalance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TBank getTBank() {
		return this.TBank;
	}

	public void setTBank(TBank TBank) {
		this.TBank = TBank;
	}

	public TUserDetail getTUserDetail() {
		return this.TUserDetail;
	}

	public void setTUserDetail(TUserDetail TUserDetail) {
		this.TUserDetail = TUserDetail;
	}

	public TUserType getTUserType() {
		return this.TUserType;
	}

	public void setTUserType(TUserType TUserType) {
		this.TUserType = TUserType;
	}

	public int getBankID() {
		return bankID;
	}

	public void setBankID(int bankID) {
		this.bankID = bankID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}