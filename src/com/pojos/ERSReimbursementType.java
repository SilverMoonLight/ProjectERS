package com.pojos;

import java.io.Serializable;

public class ERSReimbursementType implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4451024250033586666L;
	private int id;
	private String type;
	
	public ERSReimbursementType() {
		
	}
	
	public ERSReimbursementType(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}
