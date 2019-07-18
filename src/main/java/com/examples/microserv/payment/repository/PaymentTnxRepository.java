package com.examples.microserv.payment.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.examples.microserv.payment.model.PaymentTnx;

public interface PaymentTnxRepository  extends JpaRepository<PaymentTnx, Integer> {  
	/*@Query("select p from paymenttnx p where p.pspCode =:pspCode and p.paymentReceipt =:paymentReceipt")
	public List<PaymentTnx> findByPspCodeAndPaymenReceipt(@Param("pspCode") String pspCode,@Param("paymentReceipt") String paymentReceipt);*/
}
