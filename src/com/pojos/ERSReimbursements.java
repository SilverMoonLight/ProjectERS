package com.pojos;

import java.io.Serializable;
import java.util.Date;

public class ERSReimbursements implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6232481155036142863L;
	private int id;
	private double amount;
	private String description;
	private Date timeSubmitted;
	private Date timeResolved;
	private int authorId;
	private int resolverId;
	private int type;
	private int status;
	
	public ERSReimbursements(int id, double amount, String description, Date timeSubmitted, Date timeResolved, int authorId, int resolverId,
			int type, int status) {
		
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.type = type;
		this.status = status;
	}
	
	public ERSReimbursements(int id, double amount, String description, Date timeSubmitted, int authorId, int type, int status) {
		
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.timeSubmitted = timeSubmitted;
		this.authorId = authorId;
		this.type = type;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTimeSubmitted() {
		return timeSubmitted;
	}
	public void setTimeSubmitted(Date timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}
	public Date getTimeResolved() {
		return timeResolved;
	}
	public void setTimeResolved(Date timeResolved) {
		this.timeResolved = timeResolved;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
