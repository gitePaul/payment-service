package com.examples.microserv.payment.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examples.microserv.payment.model.PaymentTnx;
import com.examples.microserv.payment.repository.PaymentTnxRepository; 
@Service
public class PaymentTnxService {
	@Autowired
	PaymentTnxRepository paymentTnxRepo;
	public List<PaymentTnx> list() {
		return paymentTnxRepo.findAll();

	}

	public PaymentTnx get(int payTnxId) {
		return paymentTnxRepo.getOne(payTnxId);
	}

	public void update(PaymentTnx payTnxId) {
		paymentTnxRepo.save(payTnxId);
	}
	public void create(PaymentTnx payTnxId) {
		paymentTnxRepo.save(payTnxId);
	}

	public void delete(int payTnxId) {
		paymentTnxRepo.deleteById(payTnxId);
	}
	
/*	public List<PaymentTnx> findPaymentByPspCodeAndPaymentReceipt(String pspCode, String paymentReceipt){
		return paymentTnxRepo.findByPspCodeAndPaymenReceipt(pspCode, paymentReceipt);	
    }	 	
 
	public List<PaymentTnx> getAllPayments() {
		return paymentTnxRepo.findAll();
	}*/

    
 
}
