package com.examples.microserv.payment.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name="paymenttnx")
public class PaymentTnx {
    
	@Id
	@GeneratedValue
	private int id;	
	private String pspId;	
	private String spId;
	private Long billControlNumber;	
	private Double paidAmount;
	private String payerEmail;
	//private Date paidDate;
	//private Date receivedDate;
	//private Long paymentReceipt;	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPspId() {
		return pspId;
	}
	public void setPspId(String pspId) {
		this.pspId = pspId;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
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
	public String getPayerEmail() {
		return payerEmail;
	}
	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}	
	 
}
