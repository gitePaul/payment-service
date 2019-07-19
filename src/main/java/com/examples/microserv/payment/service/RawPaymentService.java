package com.examples.microserv.payment.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.examples.microserv.payment.model.RawPayment;
import com.examples.microserv.payment.repository.RawPaymentRepository;
@Service
public class RawPaymentService {
	@Autowired
	RawPaymentRepository rawPaymentRepo;

 	public List<RawPayment> list() {
		return rawPaymentRepo.findAll();

	}

	public RawPayment get(int rawPayId) {
		return rawPaymentRepo.getOne(rawPayId);
	}

	public void update(RawPayment rawPayment) {
		rawPaymentRepo.save(rawPayment);
	}
	public void create(RawPayment rawPayment) {
		rawPaymentRepo.save(rawPayment);
	}

	public void delete(int rawPayId) {
		rawPaymentRepo.deleteById(rawPayId);
	}
	
	public List<RawPayment> checkByPspCodeAndPaymenReceipt(@Param("pspCode") String pspCode,@Param("paymentReceipt") String paymentReceipt) {
		
		return rawPaymentRepo.checkByPspCodeAndPaymenReceipt(pspCode, paymentReceipt);
	}


}
