package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pojos.ERSReimbursementStatus;
import com.pojos.ERSReimbursementType;
import com.pojos.ERSReimbursements;
import com.pojos.ERSUser;
import com.util.ConnectionUtil;

public class ERSDAOImp implements DAO {

	@Override
	public ERSUser login(String username, String password, HttpServletRequest req) {

		ArrayList<ERSUser> usersList = new ArrayList<>();

		ERSUser currentUser = null;

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_USERS";
			PreparedStatement ps = connect.prepareStatement(sql);

			ResultSet set = ps.executeQuery();

			while (set.next()) {
				usersList.add(new ERSUser(set.getInt(1), set.getString(2), set.getString(3), set.getString(4),
						set.getString(5), set.getString(6), set.getInt(7)));
			}

			for (ERSUser user : usersList) {
				if (user.getPassword().equals(password) && user.getUsername().equals(username)) {
					currentUser = user;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (currentUser == null) {
			return null;
		}

		ArrayList<ERSReimbursementType> types = getReimbursementTypes();
		ArrayList<ERSUser> employees = new ArrayList<>();

		for (int i = 0; i < usersList.size(); i++) {
			if (usersList.get(i).getUr_id() == 1) {
				employees.add(usersList.get(i));
			}
		}

		HttpSession session = req.getSession();
		session.setAttribute("user", currentUser);
		session.setAttribute("types", types);
		session.setAttribute("users", usersList);
		session.setAttribute("employees", employees);
		session.setAttribute("requests", getCompletedReimbursements(currentUser.getU_id()));
		session.setAttribute("statuses", getStatus());
		List<ERSReimbursements> pendingReimbursemts = getPendingReimbursements();

		List<ERSReimbursements> employeePendingReimbursements = new ArrayList<>();

		for (int i = 0; i < pendingReimbursemts.size(); i++) {
			if (pendingReimbursemts.get(i).getAuthorId() == currentUser.getU_id()) {
				employeePendingReimbursements.add(pendingReimbursemts.get(i));
			}
		}

		
		
		if (currentUser.getUr_id() == 2) {

			session.setAttribute("pendingRS", pendingReimbursemts);
			session.setAttribute("allRequests", getAllReimbursements());
		} else {
			session.setAttribute("employeePending", employeePendingReimbursements);
		}

		return currentUser;
	}

	@Override
	public String url(ERSUser user) {
		if (user.getUr_id() == 1) {
			return "EmployeePage.jsp";
		} else {
			return "ManagerPage.jsp";
		}
	}

	@Override
	public ArrayList<ERSReimbursementType> getReimbursementTypes() {

		ArrayList<ERSReimbursementType> rtList = new ArrayList<>();

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT_TYPE";
			PreparedStatement ps = connect.prepareStatement(sql);

			ResultSet set = ps.executeQuery();

			while (set.next()) {
				rtList.add(new ERSReimbursementType(set.getInt(1), set.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rtList;
	}

	@Override
	public void insertReimbursement(double amount, String description, int type, int id) {

		/*
		 * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 * Date date = new Date();
		 */

		Calendar calendar = Calendar.getInstance();
		Timestamp timeStamp = new Timestamp(calendar.getTime().getTime());

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO ERS_REIMBURSEMENTS(R_AMOUNT, R_DESCRIPTION, R_SUBMITTED, "
					+ "U_ID_AUTHOR, RT_TYPE, RT_STATUS) VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, description);
			ps.setTimestamp(3, timeStamp);
			ps.setInt(4, id);
			ps.setInt(5, type);
			ps.setInt(6, 1);

			int numRowsAffected = ps.executeUpdate();

			System.out.println("Num of Rows affected - PreparedStatement: " + numRowsAffected);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ERSReimbursements> getPendingReimbursements() {

		List<ERSReimbursements> rList = new ArrayList<>();

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE R_RESOLVED IS NULL";
			PreparedStatement ps = connect.prepareStatement(sql);

			ResultSet rows = ps.executeQuery();

			while (rows.next()) {
				rList.add(new ERSReimbursements(rows.getInt(1), rows.getDouble(2), rows.getString(3), rows.getDate(5),
						rows.getInt(7), rows.getInt(9), rows.getInt(10)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rList;
	}

	@Override
	public List<ERSReimbursementStatus> getStatus() {

		List<ERSReimbursementStatus> rList = new ArrayList<>();

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT_STATUS where RS_ID > 1";
			PreparedStatement ps = connect.prepareStatement(sql);

			ResultSet rows = ps.executeQuery();

			while (rows.next()) {
				rList.add(new ERSReimbursementStatus(rows.getInt(1), rows.getString(2)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rList;
	}

	@Override
	public void updateReimbursement(int decision, int id, int which) {

		Calendar calendar = Calendar.getInstance();
		Timestamp timeStamp = new Timestamp(calendar.getTime().getTime());

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "UPDATE ERS_REIMBURSEMENTS SET R_RESOLVED = ?, U_ID_RESOLVER = ?, RT_STATUS=? WHERE R_ID = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setTimestamp(1, timeStamp);
			ps.setInt(2, id);
			ps.setInt(3, decision);
			ps.setInt(4, which);

			int numRowsAffected = ps.executeUpdate();

			System.out.println("Num of Rows affected - PreparedStatement: " + numRowsAffected);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<ERSReimbursements> getCompletedReimbursements(int id) {

		List<ERSReimbursements> rList = new ArrayList<>();

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE R_RESOLVED IS NOT NULL AND u_id_author = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rows = ps.executeQuery();

			while (rows.next()) {
				rList.add(new ERSReimbursements(rows.getInt(1), rows.getDouble(2), rows.getString(3), rows.getDate(5),
						rows.getDate(6), rows.getInt(7), rows.getInt(8), rows.getInt(9), rows.getInt(10)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rList;
	}

	@Override
	public List<ERSReimbursements> getRequestsById(int id) {

		List<ERSReimbursements> rList = new ArrayList<>();

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE u_id_author = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rows = ps.executeQuery();

			while (rows.next()) {
				if (rows.getDate(6) != null) {
					rList.add(
							new ERSReimbursements(rows.getInt(1), rows.getDouble(2), rows.getString(3), rows.getDate(5),
									rows.getDate(6), rows.getInt(7), rows.getInt(8), rows.getInt(9), rows.getInt(10)));
				} else {
					rList.add(new ERSReimbursements(rows.getInt(1), rows.getDouble(2), rows.getString(3),
							rows.getDate(5), rows.getInt(7), rows.getInt(9), rows.getInt(10)));
				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rList;
	}

	@Override
	public void updateUser(String username, String password, String email, String fname, String lname, int id) {

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "UPDATE ERS_USERS SET U_USERNAME = ?, U_PASSWORD = ?, U_EMAIL=?, U_FIRSTNAME=?, U_LASTNAME=? WHERE U_ID = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, fname);
			ps.setString(5, lname);
			ps.setInt(6, id);

			int numRowsAffected = ps.executeUpdate();

			System.out.println("Num of Rows affected - PreparedStatement: " + numRowsAffected);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ERSReimbursements> getAllReimbursements() {
		
		List<ERSReimbursements> rList = new ArrayList<>();

		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENTS";
			PreparedStatement ps = connect.prepareStatement(sql);

			ResultSet rows = ps.executeQuery();

			while (rows.next()) {
				rList.add(new ERSReimbursements(rows.getInt(1), rows.getDouble(2), rows.getString(3), rows.getDate(5),
						rows.getDate(6), rows.getInt(7), rows.getInt(8), rows.getInt(9), rows.getInt(10)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rList;
	}
	
	

}
