package com.requestObjectModel;

public class CreateUserRequestPOJO {
	int id;
	String userName;
	String password;
	
	public int getUserid() {
		return id;
	}
	public void setUserid(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String userName) {
		this.userName = userName;
	}
}
