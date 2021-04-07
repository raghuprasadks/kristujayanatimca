package com.online.garments.deal.bean;

import java.util.Date;

public class PaymentBean extends BaseBean {
	
	private long paymentId;
	private long customerId;
	private long productId;
	private Date paymentDate;
	private double amount;
	private String login;
	
	
	
	
	
	
	
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

//	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
