package com.examples.microserv.payment.controller;

import static org.hamcrest.CoreMatchers.containsString;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PaymentController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PaymentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	TestRestTemplate restTemp;
	@Test
	public void shouldGetAllPayments() throws Exception {
		Logger log = LoggerFactory.getLogger(PaymentControllerTest.class);
 
		log.info("\n--------------------All Payments---------------------------\n{}", mockMvc.toString());

		mockMvc.perform(get("/payment").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty())
		.andDo(document("payment"));

	}
	@Test
	public void shouldGetPayment() throws Exception {
		Logger log = LoggerFactory.getLogger(PaymentControllerTest.class);

		String body = "{\r\n" + " \"spCode\":\"SP102\",\r\n" + " \"pspCode\":\"PSP006\", \r\n"
				+ " \"billControlNumber\":\"991021234567\",\r\n" + " \"paidAmount\":\"50000\",  \r\n"
				+ " \"billCurrency\":\"TZS\"\r\n" + "}";
		log.info("\n--------------------Payment Request---------------------------\n{}", body);

		mockMvc.perform(put("/payment").contentType(MediaType.APPLICATION_JSON).content(body))
		        .andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().string(containsString("Paid Succesfully")))
				.andDo(document("payment"));

	}

}
