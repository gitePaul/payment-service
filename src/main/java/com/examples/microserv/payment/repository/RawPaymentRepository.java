package com.examples.microserv.payment.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.examples.microserv.payment.model.RawPayment;

public interface RawPaymentRepository  extends JpaRepository<RawPayment, Integer> {
	@Query(value = "SELECT * FROM RAWPAYMENT where PSP_CODE =:pspCode and PAYMENT_RECEIPT =:paymentReceipt", nativeQuery = true)   
	 
	public List<RawPayment> checkByPspCodeAndPaymenReceipt(@Param("pspCode") String pspCode,@Param("paymentReceipt") String paymentReceipt);


}
