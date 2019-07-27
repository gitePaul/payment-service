package com.examples.microserv.payment.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.examples.microserv.payment.dto.BillDto;
import com.examples.microserv.payment.feign.BillServiceFeign;
import com.examples.microserv.payment.model.PaymentTnx;
import com.examples.microserv.payment.model.RawPayment;
import com.examples.microserv.payment.service.PaymentTnxService;
import com.examples.microserv.payment.service.RawPaymentService;

@EnableEurekaClient
@RestController
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	BillServiceFeign billServiceFeign;
	@Autowired
	PaymentTnxService paymentTnxService;
	@Autowired
	RawPaymentService rawPaymnentService;
	@Autowired
	RestTemplate restTemp;
	StringBuilder builder = new StringBuilder();
	Logger log = LoggerFactory.getLogger(PaymentController.class);
	HttpStatus httpstatus = null;
	ResponseEntity<Object> response = null;
	BillDto verifyBill;
	BillDto updateBill;
	PaymentTnx paymentTnx;
	RawPayment rawPayment;
	Object responseEntity = null;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		restTemp = new RestTemplate();
		return restTemp;

	}

	@GetMapping(produces = "application/json")
	public List<RawPayment> rawPaymnentService() {
		return rawPaymnentService.list();
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	ResponseEntity<Object> submitPayment(@RequestBody RawPayment rawPayment, Model model,
			@Param("billControlNumber") String billControlNumber, BillDto billServiceDto) throws InterruptedException {

		// Bills from Feign Bill Service
		List<BillDto> listBillDto = billServiceFeign.getAllBills();
		for (BillDto bills : listBillDto) {
			bills.getBillControlNumber();
			bills.getBillAmount();
			bills.getCurrency();
			bills.getExpirationDate();
			System.out.println("\"Sp Code :: Control Number :: Amount :: Currency :: Expire Date \n");
			System.out.println(bills.getSpCode() + " :: " + bills.getBillControlNumber() + " :: "
					+ bills.getBillAmount() + " :: " + bills.getCurrency() + " :: " + bills.getExpirationDate());
			httpstatus = HttpStatus.OK;
		}
		
		

		// Bill Verification
		if (rawPayment.getBillControlNumber() != null && rawPayment.getSpCode() != null) {
			try {
				billServiceDto.setSpCode(rawPayment.getSpCode());
				billServiceDto.setBillControlNumber(rawPayment.getBillControlNumber());
				verifyBill = billServiceFeign.getBillDetailBySpCodeAndBillControlNumber(billServiceDto);
				httpstatus = HttpStatus.OK;
				System.out.println("\nGet bills by sp code and control number ::::\n" + verifyBill);

			} catch (Exception e) {
				log.info(new Date()
						+ "\n--------------------Failed to get bill details by sp code and control number!------------\n"
						+ e);
			}

			if (verifyBill != null) {
				if (billServiceDto.getBillAmount().doubleValue() < rawPayment.getPaidAmount().doubleValue()) {
					builder.append("Amount paid is less than billed amount : ").append(billServiceDto.getBillAmount())
							.append("\n");
					httpstatus = HttpStatus.FAILED_DEPENDENCY;

					if (billServiceDto.getCurrency() != rawPayment.getBillCurrency()) {
						builder.append("Payment currency does not match bill currency : ")
								.append(billServiceDto.getCurrency()).append("\n");
						httpstatus = HttpStatus.FAILED_DEPENDENCY;

					}

				}
			}

			else {
				try {
					rawPaymnentService.create(rawPayment);
					builder.append("Receipt No:").append(rawPayment.getPaymentReceipt()).append("\n");
					builder.append("Control No:").append(rawPayment.getBillControlNumber()).append("\n");
					builder.append("Paid Successfully\n");
					httpstatus = HttpStatus.OK;

				} catch (Exception e) {

					log.info(new Date() + "\n--------------------Failed to make payment!------------\n" + e);
				}

				// Update Bill
				if (rawPayment != null) {
					try {

						billServiceDto.setBillControlNumber(rawPayment.getBillControlNumber());
						billServiceDto.setBillAmount(rawPayment.getPaidAmount());
						billServiceDto.setStatus(true);
						updateBill = billServiceFeign.updateBillDetailByPaidStatus(billServiceDto);
						httpstatus = HttpStatus.OK;
						System.out.println("\nUpdate bills by sp code and control number ::::\n" + verifyBill);

					} catch (Exception e) {
						log.info(new Date() + "\n--------------------Failed to update bill details!------------\n" + e);
					}

				}

				/*
				 * if(rawPayment.getId()!=null) { builder.append(new Date() +
				 * ": Payment moved successfully!").append("\n"); } else { builder.append(new
				 * Date() + ": Failed to move payments from raw payments!").append("\n");
				 * httpstatus = HttpStatus.FAILED_DEPENDENCY; }
				 */

			}

		}

		return new ResponseEntity<Object>(builder.toString(), httpstatus);
	}

	@PostMapping(value = "/check", consumes = "application/json", produces = "application/json")
	public Object checkByPspCodeAndPaymenReceipt(@RequestBody RawPayment rawPayment, @Param("pspCode") String pspCode,
			@Param("paymentReceipt") String paymentReceipt) {

		try {
			responseEntity = rawPaymnentService.checkByPspCodeAndPaymenReceipt(rawPayment.getPspCode(),
					rawPayment.getPaymentReceipt());
		} catch (Exception e) {
			System.out.println("Failed to get payment details!" + e);
		}

		return responseEntity;
	}

	/*
	 * public PaymentTnx movePaymentToPaymentTnx(Model model,PaymentTnx
	 * paymentTnx,RawPayment rawPayment) { RawPayment
	 * payments=rawPaymnentService.getById(rawPayment.getId());
	 * model.addAttribute(payments); paymentTnxService.create(paymentTnx);
	 * log.info(new Date() +
	 * "\n--------------------(((((((((((((((((((())))))))))))))))---------------\n"
	 * + rawPayment.getId()+rawPayment.getBillControlNumber());
	 * 
	 * return paymentTnxService.create(paymentTnx);
	 * 
	 * }
	 */
}
