package com.pojos;

import java.io.Serializable;

public class ERSReimbursementStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3882606129762799454L;

	private int id;
	private String status;
	
	
	
	public ERSReimbursementStatus() {
	}


	public ERSReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
