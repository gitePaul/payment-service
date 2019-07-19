package com.examples.microserv.payment.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.examples.microserv.payment.repository.PaymentTnxRepository;
import com.examples.microserv.payment.repository.RawPaymentRepository;
import com.examples.microserv.payment.service.PaymentTnxService;
import com.examples.microserv.payment.service.RawPaymentService;
@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets") 
public class PaymentControllerTest {
    
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	PaymentTnxService paymentTnxService;
	@MockBean
	RawPaymentService rawPaymentService;	
	@MockBean
	PaymentTnxRepository paymentTnxRepository;
	@MockBean
    RawPaymentRepository rawPaymentRepository;	
	Logger log = LoggerFactory.getLogger(PaymentControllerTest.class);
	@Test
	public void shouldGetAllPayments() throws Exception {		
 
		log.info("\n--------------------LIST ALL PAYMENTS---------------------------\n{}",mockMvc);

		mockMvc.perform(get("/payments").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())	
		.andDo(document("payments"));
		log.info("\n--------------------END LIST PAYMENTS---------------------------\n");

	}
	@Test
	public void shouldSubmitPayment() throws Exception {
		Logger log = LoggerFactory.getLogger(PaymentControllerTest.class);

		String body = "{\r\n" + 
				"	\"spCode\":\"SP102\",\r\n" + 
				"	\"pspCode\":\"PSP006\",  \r\n" + 
				"	\"billControlNumber\":\"991021234567\",\r\n" + 
				"	\"paidAmount\":\"50000\",  \r\n" + 
				"	\"billCurrency\":\"TZS\",\r\n" + 
				"	\"payerEmail\":\"omugamba@gmail.com\",\r\n" + 
				"	\"paymentReceipt\":\"991021234567\",\r\n" + 
				"	\"paidDate\":\"31-07-2019 00:00.00\",\r\n" + 
				"	\"paymentReceivedDate\":\"31-07-2019 00:00.00\"\r\n" + 
				"}";
		log.info("\n--------------------RECEIVING PAYMENT---------------------------\n{}",mockMvc);

		mockMvc.perform(post("/payments").contentType(MediaType.APPLICATION_JSON).content(body))
		        .andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().string(containsString("Paid Succesfully")))
				.andDo(document("make-payment"));
		log.info("\n--------------------END RECEIVING PAYMENT---------------------------{}\n",mockMvc.perform(post("/payment").contentType(MediaType.APPLICATION_JSON).content(body)));

	}    
}
