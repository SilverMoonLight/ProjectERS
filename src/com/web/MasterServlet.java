package com.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO;
import com.dao.ERSDAOImp;
import com.pojos.ERSReimbursementType;
import com.pojos.ERSUser;

public class MasterServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String nextPage = new RequestHelper().process(req, res);
		req.getRequestDispatcher(nextPage).forward(req, res);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String nextPage = new RequestHelper().process(req, res);
		req.getRequestDispatcher(nextPage).forward(req, res);
		
	}

}
