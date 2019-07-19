package com.examples.microserv.payment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examples.microserv.payment.model.PaymentTnx;

public interface PaymentTnxRepository  extends JpaRepository<PaymentTnx, Integer> {  
	
}
