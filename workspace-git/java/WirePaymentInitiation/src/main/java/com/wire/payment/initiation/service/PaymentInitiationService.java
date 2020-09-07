package com.wire.payment.initiation.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wire.payment.entity.TPaymentWireDetail;
import com.wire.payment.initiation.dao.IPaymentInitiationDAO;
import com.wire.payment.initiation.dto.PaymentInitiationRequest;
import com.wire.payment.initiation.dto.SearchPaymentDTO;

@Service
public class PaymentInitiationService {
	@Autowired
	IPaymentInitiationDAO paymentInitiationDAO;

	public TPaymentWireDetail createPaymentWire(PaymentInitiationRequest request) {
		TPaymentWireDetail details = new TPaymentWireDetail();
		BeanUtils.copyProperties(request, details);
		return paymentInitiationDAO.save(details);
	}
	
	 static Specification<TPaymentWireDetail> titleOrDescriptionContainsIgnoreCase(SearchPaymentDTO searchPaymentDTO) {
	        return (root, query, cb) -> {
	        	//Constructing list of parameters
	            List<Predicate> predicates = new ArrayList<Predicate>();
	            if (!StringUtils.isEmpty(searchPaymentDTO.getTransactionStatus())) {
	            	predicates.add(cb.equal(root.get("paymentStatus"), searchPaymentDTO.getTransactionStatus()));
	            }
	            if (searchPaymentDTO.getTransactionId() != 0) {
	            	predicates.add(cb.equal(root.get("transactionId"), searchPaymentDTO.getTransactionId()));
	            }
	            if (searchPaymentDTO.getCustomerAccountId() != 0) {
	            	predicates.add(cb.equal(root.get("customerAccount"), searchPaymentDTO.getCustomerAccountId()));
	            }
	            if (!StringUtils.isEmpty(searchPaymentDTO.getFromDate())
	    				&& !StringUtils.isEmpty(searchPaymentDTO.getToDate())) {
	    			String pattern = "yyyy-MM-dd";
	    			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	    			Date fromDt = null;
	    			Date toDt = null;
	    			try {
		    			fromDt =  simpleDateFormat.parse(searchPaymentDTO.getFromDate());
		    			toDt =  simpleDateFormat.parse(searchPaymentDTO.getToDate());
	    			}
	    			catch(Exception ex) {
	    				
	    			}
	    			if(fromDt != null && toDt != null)
	    				predicates.add(cb.between(root.get("paymentDueDate"), fromDt,toDt));
	            }
	        	
	            return  cb.and(predicates.toArray(new Predicate[]{}));
	        };
	        
	        
	        /*
	          //some parameters to your method
			    String param1 = "1";
			    String paramNull = null;
			
			    CriteriaBuilder qb = em.getCriteriaBuilder();
			    CriteriaQuery cq = qb.createQuery();
			    Root<A> customer = cq.from(A.class);
			
			    //Constructing list of parameters
			    List<Predicate> predicates = new ArrayList<Predicate>();
			
			    //Adding predicates in case of parameter not being null
			    if (param1 != null) {
			        predicates.add(
			                qb.equal(customer.get("someAttribute"), param1));
			    }
			    if (paramNull != null) {
			        predicates.add(
			                qb.equal(customer.get("someOtherAttribute"), paramNull));
			    }
			    //query itself
			    cq.select(customer)
			            .where(predicates.toArray(new Predicate[]{}));
			    //execute query and do something with result
			    em.createQuery(cq).getResultList();
	         */
	    }

	public List<TPaymentWireDetail> getNotCompletePayments() {
		return paymentInitiationDAO.getInitiationList("N");
	}

	public List<TPaymentWireDetail> getCompletePayments() {
		return paymentInitiationDAO.getInitiationList("Y");
	}

	public Page<TPaymentWireDetail> searchPayments(SearchPaymentDTO searchPaymentDTO) throws Exception {
/*		if (!StringUtils.isEmpty(searchPaymentDTO.getTransactionStatus())) {
			return paymentInitiationDAO.getInitiationList(searchPaymentDTO.getTransactionStatus().toUpperCase());
		}
		if (searchPaymentDTO.getTransactionId() != 0) {
			List<TPaymentWireDetail> paymentList = new ArrayList<TPaymentWireDetail>();
			paymentList.add(paymentInitiationDAO.findById(searchPaymentDTO.getTransactionId()).get());
			return paymentList;
		}
		if (searchPaymentDTO.getCustomerAccountId() != 0) {
			return paymentInitiationDAO.getPaymentsByCustomerAccount(searchPaymentDTO.getCustomerAccountId());
		}
		if (!StringUtils.isEmpty(searchPaymentDTO.getFromDate())
				&& !StringUtils.isEmpty(searchPaymentDTO.getToDate())) {
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return paymentInitiationDAO.getPaymentsByDateRange(simpleDateFormat.parse(searchPaymentDTO.getFromDate()),
					simpleDateFormat.parse(searchPaymentDTO.getToDate()));
		}
		*/
		
		
		if (!StringUtils.isEmpty(searchPaymentDTO.getFromDate())
				&& !StringUtils.isEmpty(searchPaymentDTO.getToDate())) {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			if(simpleDateFormat.parse(searchPaymentDTO.getFromDate()).after(simpleDateFormat.parse(searchPaymentDTO.getToDate())))
			{
				throw new Exception("From date ccannot be greter then To Date");
			}
		}
		return paymentInitiationDAO.findAll(titleOrDescriptionContainsIgnoreCase(searchPaymentDTO), 
				PageRequest.of(searchPaymentDTO.getPageNo(), searchPaymentDTO.getPageSize()));
	}
}
