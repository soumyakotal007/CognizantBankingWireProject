package com.wire.payment.authorization.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wire.payment.authorization.dao.UserRepository;
import com.wire.payment.authorization.dto.PaymentAuthorizationRequest;
import com.wire.payment.entity.TUserBankDetail;
import com.wire.payment.entity.TUserDetail;


@Service
public class PaymentAuthorizationService {
	@Autowired
	UserRepository userDao;

	public boolean authorizeUser(PaymentAuthorizationRequest req) throws Exception {
		boolean result = false;

			Optional<TUserDetail> userDetails = userDao.findById(req.getMerchantId());
			if(userDetails.isPresent()) {
				TUserDetail user = userDetails.get();
				TUserBankDetail bankdetails = getBankAccountDetails(user.getTUserBankDetails(),req);
				
				if(bankdetails.getBankID() == req.getBankId()) {
					if(bankdetails.getTUserType().getUserTypeId() == 2) {
						if(bankdetails.getBankAccId() == req.getAccount()) {
						 result = true;
						}
						else {
							throw new Exception("Invalid Merchant ID - " + req.getMerchantId());
						}
					}
					else {
						throw new Exception("Invalid Merchant ID - " + req.getMerchantId());
					}
				}
				else {
					throw new Exception("Bank does not matched with - Merchant ID - " + req.getMerchantId() + " - Bank ID :: " + req.getBankId());
				}
				
			}else {
				throw new Exception("Bank detail not present for User - " + req.getMerchantId());
			}
		
		return result;
	}
	
	private TUserBankDetail getBankAccountDetails(List<TUserBankDetail> userdetailsList , PaymentAuthorizationRequest req) throws Exception {
		if(userdetailsList != null && !userdetailsList.isEmpty()) {
			return userdetailsList.stream().filter(d -> {
				if(d.getBankAccId() == req.getAccount() && d.getBankID()==req.getBankId() && d.getUserId() == req.getMerchantId()){
					return true;
				}
				return false;
			}).findFirst().orElse(new TUserBankDetail());
		}
		else {
			throw  new Exception("Invalid bank account  - Account ID : " + req.getAccount() + " - Merchant ID" + req.getMerchantId());
		}
	}
}
