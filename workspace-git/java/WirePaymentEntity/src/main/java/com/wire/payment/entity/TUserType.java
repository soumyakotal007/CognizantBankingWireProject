package com.wire.payment.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_user_type database table.
 * 
 */
@Entity
@Table(name="t_user_type")
@NamedQuery(name="TUserType.findAll", query="SELECT t FROM TUserType t")
public class TUserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_type_id")
	private int userTypeId;

	@Column(name="user_type_name")
	private String userTypeName;

	//bi-directional many-to-one association to TUserBankDetail
	@OneToMany(mappedBy="TUserType")
	private List<TUserBankDetail> TUserBankDetails;

	public TUserType() {
	}

	public int getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return this.userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public List<TUserBankDetail> getTUserBankDetails() {
		return this.TUserBankDetails;
	}

	public void setTUserBankDetails(List<TUserBankDetail> TUserBankDetails) {
		this.TUserBankDetails = TUserBankDetails;
	}

	public TUserBankDetail addTUserBankDetail(TUserBankDetail TUserBankDetail) {
		getTUserBankDetails().add(TUserBankDetail);
		TUserBankDetail.setTUserType(this);

		return TUserBankDetail;
	}

	public TUserBankDetail removeTUserBankDetail(TUserBankDetail TUserBankDetail) {
		getTUserBankDetails().remove(TUserBankDetail);
		TUserBankDetail.setTUserType(null);

		return TUserBankDetail;
	}

}