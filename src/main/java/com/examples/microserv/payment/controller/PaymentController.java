package com.examples.microserv.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import com.examples.microserv.payment.model.RawPayment;
import com.examples.microserv.payment.service.PaymentTnxService;
import com.examples.microserv.payment.service.RawPaymentService; 
@EnableEurekaClient 
@RestController 
@RequestMapping("/payments")
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
    @PostMapping
    public ResponseEntity<String> submitPayment(@RequestBody RawPayment rawPayment){
    	rawPaymnentService.create(rawPayment); 
    	 
		return new ResponseEntity<String>("Paid Succesfully", HttpStatus.CREATED);
	} 
}
