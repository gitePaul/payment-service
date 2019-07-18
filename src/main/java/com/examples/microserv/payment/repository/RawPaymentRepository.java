package com.examples.microserv.payment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examples.microserv.payment.model.RawPayment;

public interface RawPaymentRepository  extends JpaRepository<RawPayment, Integer> {

}
