package com.tektree.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;

/**
 * Servlet implementation class InternalProjectRegistration
 */
@WebServlet("/InternalProjectRegistration")
public class InternalProjectRegistration extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   
		res.setContentType("text/html");
	    //PrintWriter pw=res.getWriter();
	     
	    HttpSession session = req.getSession(false);
	    int employee_id = 0; 
	    System.out.println("In project registration");
	    try {
	        employee_id = Integer.parseInt((String)session.getAttribute("emp_id"));
	        }catch(Exception e){
	        	employee_id = (int) session.getAttribute("emp_id");
	        }
	    
	    //employee table
	    
	    String project_name = req.getParameter("pname");
	    String project_manager = req.getParameter("pm");
	    String project_lead = req.getParameter("pl");
	    java.util.Date start_date=Date.valueOf(req.getParameter("dob1"));
	    java.util.Date end_date=Date.valueOf(req.getParameter("dob2"));
	    String project_status = req.getParameter("ps");
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
			String query = "INSERT INTO \"tektree\".\"employee_project\""
					+ "(employee_id,project_name,project_manager,project_lead,"
					+ "start_date,end_date,created_on,created_by)"
					+ "VALUES("+employee_id+","+project_name+","+project_manager+","+project_lead+","
							+ ""+start_date+","+end_date+","+createdOn+","+createdBy+")";
			PreparedStatement st=con.prepareStatement(query);
			System.out.println("success");
			st.executeUpdate();
   	        st.close();
   	        session.setAttribute("emp_id",employee_id);
			
		}catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
