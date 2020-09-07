package com.wire.payment.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_user_details database table.
 * 
 */
@Entity
@Table(name="t_user_details")
@NamedQuery(name="TUserDetail.findAll", query="SELECT t FROM TUserDetail t")
public class TUserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private int userId;

	private String address;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_password")
	private String userPassword;

	@Column(name="user_role")
	private String userRole;

	//bi-directional many-to-one association to TPaymentWireDetail
	@OneToMany(mappedBy="TUserDetail1")
	private List<TPaymentWireDetail> TPaymentWireDetails1;

	//bi-directional many-to-one association to TPaymentWireDetail
	@OneToMany(mappedBy="TUserDetail2")
	private List<TPaymentWireDetail> TPaymentWireDetails2;

	//bi-directional many-to-one association to TUserBankDetail
	@OneToMany(mappedBy="TUserDetail")
	private List<TUserBankDetail> TUserBankDetails;

	public TUserDetail() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<TPaymentWireDetail> getTPaymentWireDetails1() {
		return this.TPaymentWireDetails1;
	}

	public void setTPaymentWireDetails1(List<TPaymentWireDetail> TPaymentWireDetails1) {
		this.TPaymentWireDetails1 = TPaymentWireDetails1;
	}

	public TPaymentWireDetail addTPaymentWireDetails1(TPaymentWireDetail TPaymentWireDetails1) {
		getTPaymentWireDetails1().add(TPaymentWireDetails1);
		TPaymentWireDetails1.setTUserDetail1(this);

		return TPaymentWireDetails1;
	}

	public TPaymentWireDetail removeTPaymentWireDetails1(TPaymentWireDetail TPaymentWireDetails1) {
		getTPaymentWireDetails1().remove(TPaymentWireDetails1);
		TPaymentWireDetails1.setTUserDetail1(null);

		return TPaymentWireDetails1;
	}

	public List<TPaymentWireDetail> getTPaymentWireDetails2() {
		return this.TPaymentWireDetails2;
	}

	public void setTPaymentWireDetails2(List<TPaymentWireDetail> TPaymentWireDetails2) {
		this.TPaymentWireDetails2 = TPaymentWireDetails2;
	}

	public TPaymentWireDetail addTPaymentWireDetails2(TPaymentWireDetail TPaymentWireDetails2) {
		getTPaymentWireDetails2().add(TPaymentWireDetails2);
		TPaymentWireDetails2.setTUserDetail2(this);

		return TPaymentWireDetails2;
	}

	public TPaymentWireDetail removeTPaymentWireDetails2(TPaymentWireDetail TPaymentWireDetails2) {
		getTPaymentWireDetails2().remove(TPaymentWireDetails2);
		TPaymentWireDetails2.setTUserDetail2(null);

		return TPaymentWireDetails2;
	}

	public List<TUserBankDetail> getTUserBankDetails() {
		return this.TUserBankDetails;
	}

	public void setTUserBankDetails(List<TUserBankDetail> TUserBankDetails) {
		this.TUserBankDetails = TUserBankDetails;
	}

	public TUserBankDetail addTUserBankDetail(TUserBankDetail TUserBankDetail) {
		getTUserBankDetails().add(TUserBankDetail);
		TUserBankDetail.setTUserDetail(this);

		return TUserBankDetail;
	}

	public TUserBankDetail removeTUserBankDetail(TUserBankDetail TUserBankDetail) {
		getTUserBankDetails().remove(TUserBankDetail);
		TUserBankDetail.setTUserDetail(null);

		return TUserBankDetail;
	}

}