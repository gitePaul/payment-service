package com.examples.microserv.payment.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.examples.microserv.payment.model.PaymentTnx;

public interface PaymentTnxRepository  extends MongoRepository<PaymentTnx, Integer> {  
	
}
