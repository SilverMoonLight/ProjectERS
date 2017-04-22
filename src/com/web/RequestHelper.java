package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO;
import com.dao.ERSDAOImp;
import com.pojos.ERSReimbursementStatus;
import com.pojos.ERSReimbursementType;
import com.pojos.ERSReimbursements;
import com.pojos.ERSUser;

public class RequestHelper {
	
	public String process(HttpServletRequest req, HttpServletResponse resp) {

		DAO ers = new ERSDAOImp();
		
		switch (req.getRequestURI()) {
		case "/ProjectERS/login.do": {
			
			String username = (String) req.getParameter("user");
			String password = (String) req.getParameter("password");
			
			
			ERSUser user = ers.login(username, password, req);
			if(user == null) {
				
				req.setAttribute("nouser", "No User Found");
				
				
				
				return "login.jsp";
			}
			
			
			String url = ers.url(user);
			
			return url;
		}
		
		case "/ProjectERS/reimbursements.do": {
			
			String amount = req.getParameter("amount");
			String description = req.getParameter("description");
			String type = req.getParameter("type");
			HttpSession session = req.getSession();
			ArrayList<ERSReimbursementType> types = (ArrayList<ERSReimbursementType>) session.getAttribute("types");
			ERSUser user = (ERSUser) session.getAttribute("user");
			int ers_type = 0;
			for(ERSReimbursementType rtype: types) {
				if(rtype.getType().equals(type)) {
					ers_type = rtype.getId();
				}
			}
			ArrayList<ERSReimbursements> employeePending =  (ArrayList<ERSReimbursements>) session.getAttribute("employeePending");
			
			double amounts = Double.parseDouble(amount);
			System.out.println(amounts + description + "    " + ers_type + user.getU_id());
			ers.insertReimbursement(amounts, description, ers_type, user.getU_id());
			
			List<ERSReimbursements> pendingReimbursemts = ers.getPendingReimbursements();
			
			for (int i = 0; i < pendingReimbursemts.size(); i++) {
				if (pendingReimbursemts.get(i).getAuthorId() == user.getU_id()) {
					employeePending.add(pendingReimbursemts.get(i));
				}
			}
			
			return "EmployeePage.jsp";
			
		}
		
		case "/ProjectERS/decision.do": {
			
			String decision = req.getParameter("type");
			String number = req.getParameter("number");
			int num = Integer.parseInt(number);
			HttpSession session = req.getSession();
			ERSUser user = (ERSUser) session.getAttribute("user");
			List<ERSReimbursementStatus> list = (List<ERSReimbursementStatus>) session.getAttribute("statuses");
			
			int status = 1;
			for(ERSReimbursementStatus item : list) {
				if(item.getStatus().equals(decision)) {
					status = item.getId();
				}
			}
			
			List<ERSReimbursements> requestList = (List<ERSReimbursements>) session.getAttribute("pendingRS");
		
			int i = 0;
			int index = 0;
			for(ERSReimbursements request: requestList) {
				if(request.getId() == num) {
					index = i;
				}
				i++;
			}
			
			requestList.remove(index);
			
			ers.updateReimbursement(status, user.getU_id(), num);
			
			session.setAttribute("allRequests", ers.getAllReimbursements());
			
			return "ManagerPage.jsp";
			
		} case "/ProjectERS/ViewRequest.do": {
				
			String id = req.getParameter("id");
			int userId = Integer.parseInt(id);
			HttpSession session = req.getSession();
			List<ERSReimbursements> userRequests = ers.getRequestsById(userId);
			session.setAttribute("employeeRequests", userRequests);
			
			return "EmployeeRequests.jsp";
			
		} case "/ProjectERS/Update.do": {
			
			PrintWriter out;
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String fname = req.getParameter("firstname");
			String lname = req.getParameter("lastname");
			String password2 = req.getParameter("password2");
			
			if(!password.equals(password2)) {
				
				try {
					out = resp.getWriter();
					out.write("Password do not match");
					return " ";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
				
			}
			
			
			HttpSession session = req.getSession();
			ERSUser user = (ERSUser) session.getAttribute("user");
			user.setUsername(username);
			user.setPassword(password);
			user.setFirstname(fname);
			user.setLastname(lname);
			user.setEmail(email);
			
			
			ers.updateUser(username, password, email, fname, lname, user.getU_id());
			
			
			
			return "ViewInfo.jsp";
			
		} case "/ProjectERS/Logout.do": {
			
			HttpSession session = req.getSession();
			session.invalidate();
			
			return "login.jsp";
		}
		
		default: {
			throw new IllegalArgumentException("Not a valid URI");
		}
		}
	}

}
