package com.examples.microserv.payment.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name="paymenttnx")
public class PaymentTnx {
    
	@Id
	@GeneratedValue
	//@Column(name="id", nullable=false)
	private int id;
	//@Column(name="pspId", nullable=true)
	private String pspId;
	//@Column(name="spId", nullable=true)
	private String spId;
	//@Column(name="billControlNumber", nullable=true)
	private Long billControlNumber;
	//@Column(name="paidAmount", nullable=true)
	private Double paidAmount;
	//private Date paidDate;
	//private Date receivedDate;
	//private Long paymentReceipt;
	//@Column(name="payerEmail", nullable=true)
	private String payerEmail;
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
 
	
	
}
