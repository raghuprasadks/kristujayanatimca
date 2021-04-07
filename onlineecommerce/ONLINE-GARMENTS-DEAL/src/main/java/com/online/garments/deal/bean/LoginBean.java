package com.online.garments.deal.bean;

public class LoginBean extends  BaseBean {
	
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

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
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

	//@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	//@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return login;
	}

}
