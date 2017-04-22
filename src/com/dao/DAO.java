package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pojos.ERSReimbursementStatus;
import com.pojos.ERSReimbursementType;
import com.pojos.ERSReimbursements;
import com.pojos.ERSUser;

public interface DAO {

	public ERSUser login(String username, String password, HttpServletRequest request);
	public String url(ERSUser user);
	public ArrayList<ERSReimbursementType> getReimbursementTypes();
	public void insertReimbursement(double amount, String description, int type, int id);
	public List<ERSReimbursements> getPendingReimbursements();
	public List<ERSReimbursementStatus> getStatus();
	public void updateReimbursement(int decision, int id, int which);
	public List<ERSReimbursements> getCompletedReimbursements(int id);
	public List<ERSReimbursements> getRequestsById(int id);
	public void updateUser(String username, String password, String email, String fname, String lname, int id);
	public List<ERSReimbursements> getAllReimbursements();
}
