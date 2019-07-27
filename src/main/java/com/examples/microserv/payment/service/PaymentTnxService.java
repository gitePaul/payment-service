package com.examples.microserv.payment.service;

import java.util.List;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.examples.microserv.payment.model.PaymentTnx;
import com.examples.microserv.payment.repository.PaymentTnxRepository;

@Service
public class PaymentTnxService {
	@Autowired
	PaymentTnxRepository paymentTnxRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	Query query;
	Logger log = LoggerFactory.getLogger(PaymentTnxService.class);

	public List<PaymentTnx> listAllPayments() {
		try {
			paymentTnxRepo.findAll();
		} catch (ConstraintViolationException e) {

			log.info("Failed to get list of payments from the database !" + e.getMessage());
		}
		return paymentTnxRepo.findAll();
	}
	public PaymentTnx create(PaymentTnx paymentTnx) {

		try {
			paymentTnxRepo.save(paymentTnx);
		} catch (ConstraintViolationException e) {

			log.info("Failed to save to the database !" + e.getMessage());
		}
		return paymentTnx;

	}

	public PaymentTnx checkByPspCodeAndPaymenReceipt(@Param("pspCode") String pspCode,
			@Param("paymentReceipt") String paymentReceipt) {

		try {
			query = new Query();
			query.addCriteria(Criteria.where("pspCode").is(pspCode).and("paymentReceipt").is(paymentReceipt));
		} catch (ConstraintViolationException e) {

			log.info("Failed to get payments from the database !" + e.getMessage());
		}
		return mongoTemplate.findOne(query, PaymentTnx.class);
	}
	 
}
