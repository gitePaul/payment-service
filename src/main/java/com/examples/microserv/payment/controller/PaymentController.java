package com.examples.microserv.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import com.examples.microserv.payment.model.PaymentTnx;
import com.examples.microserv.payment.model.RawPayment;
import com.examples.microserv.payment.service.PaymentTnxService;
import com.examples.microserv.payment.service.RawPaymentService; 
 
@RestController 
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired 
	PaymentTnxService paymentTnxService;	
	@Autowired 
	RawPaymentService rawPaymnentService;
	 
    @GetMapping
	public List<RawPayment> rawPaymnentService()
	{		 
	  return rawPaymnentService.list();
	}
    @PutMapping
    public ResponseEntity<String> payBill(@RequestBody RawPayment rawPayment){
    	rawPaymnentService.create(rawPayment); 
    	 
		return new ResponseEntity<String>("Paid Succesfully", HttpStatus.CREATED);
	}
	 
/*	@PostMapping("/check")
	public List<PaymentTnx> findPaymentByPspCodeAndPaymentReceipt(@RequestParam String pspCode, String paymentReceipt)
	{
		if(pspCode != null && paymentReceipt != null)
			return paymentService.findPaymentByPspCodeAndPaymentReceipt(pspCode, paymentReceipt);
		else
			return null;
	}	*/

	/*@PostMapping("/pay-bill")
	public ResponseEntity<String> payBill(RawPayment rawPayment){
	    rawPaymentService.receivePayment(rawPayment);
		return new ResponseEntity<String>("Paid Successfully", HttpStatus.OK);
	}	
	*/
	
}
