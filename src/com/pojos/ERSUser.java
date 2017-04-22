package com.pojos;

import java.io.Serializable;

public class ERSUser implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -7618154965425809078L;
	
	private int u_id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int ur_id;
	
	
	
	public ERSUser() {
		
	}



	public ERSUser(int u_id, String username, String password, String firstname, String lastname, String email,
			int ur_id) {
		
		this.u_id = u_id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.ur_id = ur_id;
	}



	public int getU_id() {
		return u_id;
	}



	public void setU_id(int u_id) {
		this.u_id = u_id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getUr_id() {
		return ur_id;
	}



	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}
	
	
	
	

}
