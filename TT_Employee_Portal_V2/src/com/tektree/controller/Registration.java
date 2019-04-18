package com.tektree.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		
		res.setContentType("text/html");
	    //PrintWriter pw=res.getWriter();
	    
	    HttpSession session = req.getSession(false);
	    
	    //retrieving form parameters
	    //hr_employee table
	    //String emp_code = req.getParameter("ec");
	    //String fname = req.getParameter("fn");
	    //String designation=req.getParameter("designation");    
	    //String skill = req.getParameter("skill");
	    //String cur_loc = req.getParameter("cl");
	    //String total_exp = req.getParameter("exp");
	    
	    //employee table
	    String employee_code = req.getParameter("employee_code");
	    String first_name = req.getParameter("first_name");
	    String middle_name = req.getParameter("middle_name");
	    String last_name = req.getParameter("last_name");
	    java.util.Date dob=Date.valueOf(req.getParameter("dob"));
	    String aadhar_no = req.getParameter("aadhar_no");
	    String pan_no = req.getParameter("pan_no");
	    String passport_no = req.getParameter("passport_no");
	    String blood_group = req.getParameter("blood_group");
	    String is_married = req.getParameter("marital_status");
	    java.util.Date anniversary_date=null;
	    try{
	    anniversary_date = Date.valueOf(req.getParameter("anniversary_date"));
	    }catch(Exception e){
	    	
	    }
	    String is_external = req.getParameter("is_external");
	    String background_verified_by = req.getParameter("background_verified_by");
	    String employee_type = req.getParameter("employee_type");
	    String designation=req.getParameter("designation"); 
	    String skill = req.getParameter("skill");
	    String current_location = req.getParameter("current_location");
	    String total_experience = req.getParameter("total_experience");
	    int ctc = Integer.parseInt(req.getParameter("ctc"));
	    java.util.Date doj=Date.valueOf(req.getParameter("doj"));
	    String gender = req.getParameter("gender");
	    
	    int employee_id = Integer.parseInt(passport_no);
	    //Created on date
	    Date createdOn = null;
	   
		//created by
		String createdBy = null;
	    
		
		Date updatedOn = null;
	    String updatedBy = null;
	    
	    try {
	    	updatedOn = new Date(session.getCreationTime());
	    	updatedBy = (String)session.getAttribute("user");
	    	createdOn = new Date(session.getCreationTime());
	    	createdBy = (String)session.getAttribute("user");
	    }catch(Exception e) {
	    	
	    }
	   
		//Database Connection
	    Connection con = null;
		try {
			con = ConnectionManager.getConnection();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	    try {
   	    	PreparedStatement st=con.prepareStatement("INSERT INTO \"tektree\".\"employee\"(employee_id,employee_code,"
   	    		+ "first_name,middle_name,last_name,dob,aadhar_no,pan_no,passport_no,blood_group,is_married,"
   	    		+ "anniversary_date,is_external,background_verified_by,employee_type,designation,skill,"
   	    		+ "current_location,total_experience,ctc,created_on,created_by,last_updated_on,last_updated_by,"
   	    		+ "doj,gender) "
   	    		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
   	 
   	    	st.setInt(1,employee_id);
   			st.setString(2,employee_code);
   			st.setString(3,first_name);
   			st.setString(4,middle_name);
   			st.setString(5,last_name);
   			st.setDate(6,(Date)dob);
   			st.setString(7,aadhar_no);
   			st.setString(8,pan_no);
   			st.setString(9,passport_no);
   			st.setString(10,blood_group);
   			st.setString(11,is_married);
   			st.setDate(12,(Date)anniversary_date);
   			st.setString(13,is_external);
   			st.setString(14,background_verified_by);
   			st.setString(15,employee_type);
   			st.setString(16,designation);
   			st.setString(17,skill);
   			st.setString(18,current_location);
   			st.setString(19,total_experience);
   			st.setInt(20,ctc);
   			st.setDate(21,(Date)createdOn);
   			st.setString(22,createdBy);
   			st.setDate(23,(Date)updatedOn);
   			st.setString(24,updatedBy);
   			st.setDate(25,(Date)doj);
   			st.setString(26,gender);
   	    	
   	        st.executeUpdate();
   	        st.close();
   	     session.setAttribute("emp_id",employee_id);
   	        	   } 
   	    catch (SQLException e) {
			e.printStackTrace();    
	    
	    }
   	// RequestDispatcher rd = req.getRequestDispatcher("TT_RegistrationForm_TT_html.html");
 	// rd.include(req, res);
 	//RequestDispatcher rd3 = req.getRequestDispatcher("RegistrationList");
        //rd3.include(req, res);
 	 	
	}	
 }
 	 
		


