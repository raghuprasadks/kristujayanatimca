package com.online.garments.deal.bean;

public class ProductBean extends BaseBean{

	
	private String productName;
	private String productQuantity;
	private String productChoice;
	private String itemCode;
	private String image;
	private double price;
	
	
	

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductChoice() {
		return productChoice;
	}

	public void setProductChoice(String productChoice) {
		this.productChoice = productChoice;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	//@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	//@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return productName;
	}

}
