package com.examples.microserv.payment.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
 
@Entity
@Table(name="rawpayment")
public class RawPayment {
	
	@Id	 
	@GeneratedValue
	//@Column(name="id", nullable=false)
	private int id;
	//@Column(name="spCode", nullable=true)
	private String spCode;
	//@Column(name="pspCode", nullable=true)
	private String pspCode;
	//@Column(name="billControlNumber", nullable=true)
	private Long billControlNumber;
	//@Column(name="paidAmount", nullable=true)
	private Double paidAmount;
	//@Column(name="billCurrency", nullable=true)
	private String billCurrency;
	//private Date paidDate;
	//private Date paymentReceivedDate;
	//private Date paymentReceipt;
	//private String payerEmail;
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
 
	
}
