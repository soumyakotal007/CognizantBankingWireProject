package com.wire.payment.initiation.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wire.payment.entity.TPaymentWireDetail;

@Repository
public interface IPaymentInitiationDAO extends JpaRepository<TPaymentWireDetail, Integer> ,JpaSpecificationExecutor<TPaymentWireDetail>{
  public List<TPaymentWireDetail> findByCustomerId(int userID);
  public List<TPaymentWireDetail> findByMerchantId(int userID);
  
  @Query("SELECT w FROM TPaymentWireDetail w WHERE w.paymentStatus = :payStat")
  public List<TPaymentWireDetail> getInitiationList(@Param("payStat") String paymentStatus);
  
  @Query("SELECT w FROM TPaymentWireDetail w WHERE w.customerAccount = :custAcc")
  public List<TPaymentWireDetail> getPaymentsByCustomerAccount(@Param("custAcc") long custAccId);
  
  @Query("SELECT w FROM TPaymentWireDetail w WHERE payment_due_date BETWEEN :startTime AND :endTime")
  List<TPaymentWireDetail> getPaymentsByDateRange(
      @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
