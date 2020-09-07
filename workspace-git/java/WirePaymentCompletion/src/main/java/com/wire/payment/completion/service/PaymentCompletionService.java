package com.wire.payment.completion.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wire.payment.completion.client.PaymentVerificationClient;
import com.wire.payment.completion.dao.BankDetailsDAO;
import com.wire.payment.completion.dao.MyUserDetails;
import com.wire.payment.completion.dao.PaymentCompletionDAO;
import com.wire.payment.completion.dao.UserRepository;
import com.wire.payment.completion.dto.PaymentCompletionRequest;
import com.wire.payment.completion.dto.PaymentUpdateRequest;
import com.wire.payment.completion.dto.PaymentVerificationRequest;
import com.wire.payment.completion.dto.PaymentVerificationResponse;
import com.wire.payment.entity.TPaymentWireDetail;
import com.wire.payment.entity.TUserBankDetail;
import com.wire.payment.entity.TUserDetail;

@Service
public class PaymentCompletionService {

	@Autowired
	PaymentCompletionDAO paymentDao;

	@Autowired
	UserRepository userDao;

	@Autowired
	BankDetailsDAO bankDao;

	@Autowired(required = false)
	PaymentVerificationClient paymentVerificationClient;
	
	
	public TPaymentWireDetail updatePayment(PaymentUpdateRequest req) throws Exception {
		Optional<TPaymentWireDetail> wiredetails = paymentDao.findById(req.getTransactionId());
		if (wiredetails.isPresent()) {
			TPaymentWireDetail wire = wiredetails.get();
			wire.setCustomerAccount(req.getCustomerAccount());
			wire.setMerchantAccount(req.getMerchantAccount());
			wire.setPaymentAmt(req.getPaymentAmt());
			String pattern = "yyyy-MM-dd";
			if(req.getPaymentDueDate() != null && req.getPaymentDueDate().contains("/")) {
				pattern = "dd/MM/yyyy";
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			wire.setPaymentDueDate(simpleDateFormat.parse(req.getPaymentDueDate()));
			return paymentDao.save(wire);
		}
		else {
			throw new Exception("Invalid wire ID - " + req.getTransactionId());
		}
	}
	
	/*@LoadBalanced
	  @Bean
	  RestTemplate restTemplate() {
	      return new RestTemplate();
	  }*/

	public TPaymentWireDetail completePayment(PaymentCompletionRequest req, Map<String, String> headerMap)
			throws Exception {
		Optional<TPaymentWireDetail> wiredetails = paymentDao.findById(req.getWireID());
		if (wiredetails.isPresent()) {
			TPaymentWireDetail wire = wiredetails.get();
			if (validateWire(wire, req, headerMap)) {
				TUserBankDetail customerDetails = getBankdetails(wire.getCustomerId() ,wire.getCustomerBankId(),wire.getCustomerAccount());
				TUserBankDetail merchantrDetails = getBankdetails(wire.getMerchantId(),wire.getMerchantBankId(),wire.getMerchantAccount());
				customerDetails.setBankBalance(customerDetails.getBankBalance().subtract(req.getPaymentAmount()));
				merchantrDetails.setBankBalance(merchantrDetails.getBankBalance().add(req.getPaymentAmount()));
				bankDao.save(customerDetails);
				bankDao.save(merchantrDetails);
				wire.setPaymentStatus("Y");
				wire.setCustomerAccount(customerDetails.getBankAccId());
				wire.setMerchantAccount(merchantrDetails.getBankAccId());
				wire = paymentDao.save(wire);
			}
			return wire;
		} else {
			throw new Exception("Invalid wire ID - " + req.getWireID());
		}
	}

	private boolean validateWire(TPaymentWireDetail wire, PaymentCompletionRequest req, Map<String, String> headerMap)
			throws Exception {
		if (!(wire.getPaymentAmt().compareTo(req.getPaymentAmount()) == 0)) {
			throw new Exception("Payment transaction amount not matched - " + req.getPaymentAmount());
		}
		if (wire.getPaymentStatus().equalsIgnoreCase("Y")) {
			throw new Exception("Payment already completed for this wire ID");
		}
		if (!ifSameDayTransaction(wire.getPaymentDueDate())) {
			throw new Exception("Payment Wire ID already expired");
		}
		//return checkVerificationServiceStatus(wire, headerMap);
		return true;
	}

	private boolean checkVerificationServiceStatus(TPaymentWireDetail wire, Map<String, String> headerMap)
			throws Exception {
		MyUserDetails details = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(details);
		System.out.println("Header Map - " + headerMap);
		PaymentVerificationRequest paymentVerificationRequest = new PaymentVerificationRequest();
		paymentVerificationRequest.setBankId(wire.getCustomerBankId());
		paymentVerificationRequest.setCustomerId(wire.getCustomerId());
		paymentVerificationRequest.setPaymentAmount(wire.getPaymentAmt());
		Map<String, String> headerMapNew = new HashMap<>();
		headerMapNew.put("PAYMENT-VERIFICATION-VERSION", headerMap.get("PAYMENT-VERIFICATION-VERSION"));
		headerMapNew.put("Authorization", headerMap.get("Authorization"));
		headerMapNew.put("Content-Type", "application/json");
		PaymentVerificationResponse response = paymentVerificationClient.paymentVerification(headerMapNew,paymentVerificationRequest);
		if (response.getResponseCode() == HttpStatus.SC_OK) {
			return true;
		} else {
			throw new Exception("Error in calling Payment Verification Service - " + response.getClientMessage());
		}
	}

	public TUserBankDetail getBankdetails(int userId,int bankId,long account) throws Exception {
		Optional<TUserDetail> userDetails = userDao.findById(userId);
		if (userDetails.isPresent()) {
			return getBankAccountDetails(userDetails.get().getTUserBankDetails(), bankId, account, userId);
		}
		else {
			throw new Exception("Invalid bank user - User ID : " +userId + " Bank ID : " + bankId + "Account ID -" + account);
		}
	}
	
	private TUserBankDetail getBankAccountDetails(List<TUserBankDetail> userdetailsList ,int bankId,long account,int userId) throws Exception {
		if(userdetailsList != null && !userdetailsList.isEmpty()) {
			return userdetailsList.stream().filter(d -> {
				if(d.getBankAccId() == account && d.getBankID()==bankId && d.getUserId() == userId){
					return true;
				}
				return false;
			}).findFirst().orElse(new TUserBankDetail());
		}
		else {
			throw  new Exception("Invalid bank account  - Account ID : " + account + " - User ID" + userId);
		}
	}

	private boolean ifSameDayTransaction(Date paymentDate) {
		Date currentDate = new Date(System.currentTimeMillis());
		return currentDate.equals(currentDate);
	}

	
/*	public  callRestSevice(String url , HttpMethod method ,  Map<String,String> headerMap ,TPaymentWireDetail wire) {
		PaymentVerificationRequest paymentVerificationRequest = new PaymentVerificationRequest();
		paymentVerificationRequest.setBankId(wire.getCustomerBankId());
		paymentVerificationRequest.setCustomerId(wire.getCustomerId());
		paymentVerificationRequest.setPaymentAmount(wire.getPaymentAmt());

		HttpHeaders headers = new HttpHeaders();
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			headers.add(entry.getKey(), entry.getValue());
		}

		HttpEntity<PaymentVerificationRequest> requestEntity = 
		     new HttpEntity<>(paymentVerificationRequest, headers);
		PaymentVerificationResponse response = 
		   gette.exchange("url", HttpMethod.POST, requestEntity, 
				   PaymentVerificationResponse.class);
	}*/
}
