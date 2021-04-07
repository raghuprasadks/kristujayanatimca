package com.online.garments.deal.bean;

public class ManagerBean extends BaseBean {
	
	
	private String Name;
	private String emailId;
	private String contactNo;
	private String address;
	
	
	private String login;
	private String password;
	private long role;
	private String confirmPassword;
	
	
	
	
	
	

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
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
