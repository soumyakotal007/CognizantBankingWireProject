package com.wire.payment.completion.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wire.payment.entity.TUserBankDetail;



public interface BankDetailsDAO extends JpaRepository<TUserBankDetail, Integer>{

}
