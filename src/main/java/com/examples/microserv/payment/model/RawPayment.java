package com.examples.microserv.payment.model; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="rawpayment")
public class RawPayment {
	
	@Id	 
	@GeneratedValue
	private int id;	
	private String spCode;
	private String pspCode;
	private Long billControlNumber;
	private String paymentReceipt;
	private Double paidAmount;
	private String billCurrency;
	private String payerEmail;
	@JsonFormat
	(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private String paidDate;
	@JsonFormat
	(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private String paymentReceivedDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}
	public String getPspCode() {
		return pspCode;
	}
	public void setPspCode(String pspCode) {
		this.pspCode = pspCode;
	}
	public Long getBillControlNumber() {
		return billControlNumber;
	}
	public void setBillControlNumber(Long billControlNumber) {
		this.billControlNumber = billControlNumber;
	}
	public String getPaymentReceipt() {
		return paymentReceipt;
	}
	public void setPaymentReceipt(String paymentReceipt) {
		this.paymentReceipt = paymentReceipt;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getBillCurrency() {
		return billCurrency;
	}
	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}
	public String getPayerEmail() {
		return payerEmail;
	}
	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public String getPaymentReceivedDate() {
		return paymentReceivedDate;
	}
	public void setPaymentReceivedDate(String paymentReceivedDate) {
		this.paymentReceivedDate = paymentReceivedDate;
	}
      
	
}
