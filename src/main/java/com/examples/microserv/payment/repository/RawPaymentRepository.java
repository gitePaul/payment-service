package com.examples.microserv.payment.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.examples.microserv.payment.model.RawPayment;
public interface RawPaymentRepository extends MongoRepository<RawPayment, Integer> {
	RawPayment findById(String rawPayId); 
	
}
