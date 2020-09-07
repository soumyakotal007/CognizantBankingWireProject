package com.wire.payment.verification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wire.payment.entity.TUserBankDetail;
import com.wire.payment.entity.TUserDetail;
import com.wire.payment.verification.dao.UserRepository;
import com.wire.payment.verification.dto.PaymentVerificationRequest;

@Service
public class UserRepositoryService {
	
	@Autowired
	UserRepository userDao;

	public boolean verifyUser(PaymentVerificationRequest req) throws Exception {
		boolean result = false;

			Optional<TUserDetail> userDetails = userDao.findById(req.getCustomerId());
			if(userDetails.isPresent()) {
				TUserDetail user = userDetails.get();
				TUserBankDetail bankdetails = getBankAccountDetails(user.getTUserBankDetails(), req);
				if(bankdetails.getBankID() == req.getBankId()) {
					if(bankdetails.getBankAccId() == req.getAccount()) {
						if((bankdetails.getBankBalance().compareTo(req.getPaymentAmount()) == 1 
								|| bankdetails.getBankBalance().compareTo(req.getPaymentAmount())  == 0)
								&& bankdetails.getTUserType().getUserTypeId() == 1) {
							result = true;
						}
						else {
							throw new Exception("Insufficient bank balance - customer ID - " + req.getCustomerId() + " - Bank ID" + req.getBankId() + " - Account ID" + req.getAccount() + " :: Bank Balance - " + bankdetails.getBankBalance());
						}
					}
					else {
						throw new Exception("Bank account does not matched with - customer ID - " + req.getCustomerId() + " - Bank ID" + req.getBankId() + " - Account ID" + req.getAccount());
					}
				}
				else {
					throw new Exception("Bank does not matched with - customer ID - " + req.getCustomerId() + " - Bank ID" + req.getBankId());
				}
			}else {
				throw new Exception("Bank detail not present for customer -" + req.getCustomerId());
			}
		
		return result;
	}
	
	private TUserBankDetail getBankAccountDetails(List<TUserBankDetail> userdetailsList , PaymentVerificationRequest req) throws Exception {
		if(userdetailsList != null && !userdetailsList.isEmpty()) {
			return userdetailsList.stream().filter(d -> {
				if(d.getBankAccId() == req.getAccount() && d.getBankID()==req.getBankId() && d.getUserId() == req.getCustomerId()){
					return true;
				}
				return false;
			}).findFirst().orElse(new TUserBankDetail());
		}
		else {
			throw  new Exception("Invalid bank account  - Account ID : " + req.getAccount() + " - Customer ID" + req.getCustomerId());
		}
	}
	
}
