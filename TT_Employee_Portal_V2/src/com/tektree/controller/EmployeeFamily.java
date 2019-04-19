package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;
@WebServlet("/EmployeeFamily")
public class EmployeeFamily extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		
		res.setContentType("text/html");
	    PrintWriter pw=res.getWriter();
	    
	    HttpSession session = req.getSession(false);
	    
	    int employee_id = 0;
        int employee_id1 = 0;
        int employee_id2 = 0;
        
        try {
            employee_id = Integer.parseInt((String)session.getAttribute("emp_id"));
            }catch(Exception e){
            	employee_id = (int) session.getAttribute("emp_id");
            }
       /* try {
        employee_id1 = Integer.parseInt((String)session.getAttribute("emp_id"));
	    employee_id2 = (int) session.getAttribute("emp_id");
        }
        catch(Exception e){	
        }
        if(employee_id1 != 0) {
        	employee_id = employee_id1;
        }
        if(employee_id2 != 0) {
        	employee_id = employee_id2;
        }*/
	   
	    
	    //Created on date
	    Date createdOn = null;
	   
		//created by
		String createdBy = null;
		
		Date updatedOn = null;
	    String updatedBy = null;
	    int employee_family_id = employee_id;
	    
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
	    	String query = "INSERT INTO \"tektree\".\"employee_family\"(employee_id,"
	    		+ "relationship,member_name,dob,work_study_place,work_study_info,email_id,"	    		
	    		+ "phone_1,created_on,created_by) "
	    		+ "VALUES("+employee_id+",";
	    	String query1 = "INSERT INTO \"tektree\".\"employee_family\"(employee_id,"
		    		+ "relationship,member_name,dob,work_study_place,work_study_info,email_id,"	    		
		    		+ "phone_1,created_on,created_by) "
		    		+ "VALUES("+employee_id+",";
	    //multiple inputs
	    Enumeration<String> en=req.getParameterNames();
	    int j = 1;
	    String[] store = new String[50];
	    while(en.hasMoreElements())
		{
	    	String paramName = en.nextElement();
            String[] paramValues = req.getParameterValues(paramName);
             
	    	 for (int i = 0; i < paramValues.length; i++) {
	                String paramValue = paramValues[i];	
	               
	                store[j] = String.valueOf(paramValue);
	                
	                j++;
	            }
		}
	   
	    int count = 0;
	    for(int i = 1; i <= j-1 ;i++) {
	    	
	    	query1 = query1 + "'"+store[i]+"',";
	    	
	    	if(i%7==0 ) {
	    		count++;
	    		query1 = query1 + "'"+createdOn+"'" + "," + "'"+createdBy+"'" + ");";
	    		
	    		PreparedStatement st=con.prepareStatement(query1);
	    		st.executeUpdate();
	    		if(i<j) {
	    		query1 = query;
	    		
	    		}
	    	}
	    } 
	    
	    	 } 
	    catch (SQLException e) {
			e.printStackTrace();    
	    
	    }
   	session.setAttribute("emp_id",employee_id); 
   	RequestDispatcher rd1 = req.getRequestDispatcher("TT_Family_details.html");
    rd1.forward(req, res);
    session.setAttribute("emp_id",employee_id); 	
}
}