package com.online.garments.deal.bean;

public class CustomerBean extends BaseBean {

	
	private String Name;
	private String age;
	private String address;
	private String contectNo;
	private String productChoice;
	private String itemCode;
	private String MultipleItems;
	private String login;
	private String password;
	private String confirmPassword;
	
	
	
	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContectNo() {
		return contectNo;
	}

	public void setContectNo(String contectNo) {
		this.contectNo = contectNo;
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

	public String getMultipleItems() {
		return MultipleItems;
	}

	public void setMultipleItems(String multipleItems) {
		MultipleItems = multipleItems;
	}

	//@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	//@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return Name;
	}

	
	
	
	
}
