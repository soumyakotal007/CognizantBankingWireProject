package com.wire.payment.completion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wire.payment.entity.TPaymentWireDetail;

@Repository
public interface PaymentCompletionDAO extends JpaRepository<TPaymentWireDetail, Integer>{
  public List<PaymentCompletionDAO> findByCustomerId(int userID);
  public List<PaymentCompletionDAO> findByMerchantId(int userID);
}
