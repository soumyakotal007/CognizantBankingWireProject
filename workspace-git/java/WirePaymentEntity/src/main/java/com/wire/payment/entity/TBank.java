package com.wire.payment.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_bank database table.
 * 
 */
@Entity
@Table(name="t_bank")
@NamedQuery(name="TBank.findAll", query="SELECT t FROM TBank t")
public class TBank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bank_id")
	private int bankId;

	private String address;

	@Column(name="bank_name")
	private String bankName;

	private String city;

	//bi-directional many-to-one association to TUserBankDetail
	@OneToMany(mappedBy="TBank")
	private List<TUserBankDetail> TUserBankDetails;

	public TBank() {
	}

	public int getBankId() {
		return this.bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<TUserBankDetail> getTUserBankDetails() {
		return this.TUserBankDetails;
	}

	public void setTUserBankDetails(List<TUserBankDetail> TUserBankDetails) {
		this.TUserBankDetails = TUserBankDetails;
	}

	public TUserBankDetail addTUserBankDetail(TUserBankDetail TUserBankDetail) {
		getTUserBankDetails().add(TUserBankDetail);
		TUserBankDetail.setTBank(this);

		return TUserBankDetail;
	}

	public TUserBankDetail removeTUserBankDetail(TUserBankDetail TUserBankDetail) {
		getTUserBankDetails().remove(TUserBankDetail);
		TUserBankDetail.setTBank(null);

		return TUserBankDetail;
	}

}