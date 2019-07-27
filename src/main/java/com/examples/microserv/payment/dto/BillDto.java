package com.examples.microserv.payment.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class BillDto {    
	private String  id;
	private String billControlNumber;
	private String spCode;
	private BigDecimal billAmount;
	private Date ReceiveDate;
	private Date expirationDate;
	private String serviceId;
	private String Currency;
	private String customerEmail;
	private Boolean status;
	private String paymentReceipt;
	public BillDto() {
		super();

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBillControlNumber() {
		return billControlNumber;
	}
	public void setBillControlNumber(String billControlNumber) {
		this.billControlNumber = billControlNumber;
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	public BigDecimal getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	public Date getReceiveDate() {
		return ReceiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		ReceiveDate = receiveDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getPaymentReceipt() {
		return paymentReceipt;
	}
	public void setPaymentReceipt(String paymentReceipt) {
		this.paymentReceipt = paymentReceipt;
	}
	@Override
	public String toString() {
		return "BillDto [id=" + id + ", billControlNumber=" + billControlNumber + ", spCode=" + spCode + ", billAmount="
				+ billAmount + ", ReceiveDate=" + ReceiveDate + ", expirationDate=" + expirationDate + ", serviceId="
				+ serviceId + ", Currency=" + Currency + ", customerEmail=" + customerEmail + ", status=" + status
				+ ", paymentReceipt=" + paymentReceipt + "]";
	}
 
	 
 
}
