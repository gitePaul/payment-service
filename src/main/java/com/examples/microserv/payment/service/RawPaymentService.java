package com.examples.microserv.payment.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.examples.microserv.payment.model.RawPayment;
import com.examples.microserv.payment.repository.RawPaymentRepository;
@Service
public class RawPaymentService {
	@Autowired
	RawPaymentRepository rawPaymentRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	Query query;
 	public List<RawPayment> list() {
		return rawPaymentRepo.findAll();

	}
	public Optional<RawPayment> get(int rawPayId) {
		return rawPaymentRepo.findById(rawPayId);
	} 
	public RawPayment getById(String rawPayId) {
		return rawPaymentRepo.findById(rawPayId);
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
	
	public RawPayment checkByPspCodeAndPaymenReceipt(@Param("pspCode") String pspCode,@Param("paymentReceipt") String paymentReceipt) {
		query = new Query();
		query.addCriteria(Criteria.where("pspCode").is(pspCode).and("paymentReceipt").is(paymentReceipt)); 		
		return mongoTemplate.findOne(query, RawPayment.class);
	}
 

}
