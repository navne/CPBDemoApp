package com.sada.web.model;

public class UserCredential {

	private String userName;
	private String password;
	private String passwordMF;
	
	public UserCredential() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserCredential(String userName, String password, String passwordMF) {
		super();
		this.userName = userName;
		this.password = password;
		this.passwordMF = passwordMF;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordMF() {
		return passwordMF;
	}

	public void setPasswordMF(String passwordMF) {
		this.passwordMF = passwordMF;
	}
	
	
	
}
