
package com.tektree.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;

/**
 * Servlet implementation class EducationDetails
 */
@WebServlet("/EmployeeEducation")
public class EmployeeEducation extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		
		res.setContentType("Text/html");
		PrintWriter pw= res.getWriter();
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
		String query = "INSERT INTO \"tektree\".\"employee_education\""
				+ "(employee_id,degree,qualification,institute_name,passed_year,score_percentage,created_on,created_by)"
				+ "VALUES("+employee_id+",";
	    	String query1 = "INSERT INTO \"tektree\".\"employee_education\""
	    			+ "(employee_id,degree,qualification,institute_name,passed_year,score_percentage,created_on,created_by)"
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
	                //query1 = query1 + "paramName";
	                //System.out.println(paramValue);
	                store[j] = String.valueOf(paramValue);
	                //System.out.println(store[j]);
	                j++;
	            }
		}
	    System.out.println(j);
	    int count = 0;
	    for(int i = 1; i <= j-1 ;i++) {
	    	//if(store[3]=="") {
	    	//	store[3]= null;
	    	//}
	    	query1 = query1 + "'"+store[i]+"',";
	    	//System.out.println(store[i]);
	    	//System.out.println(i);
	    	if(i%5==0 ) {
	    		count++;
	    		query1 = query1 + "'"+createdOn+"'" + "," + "'"+createdBy+"'" + ");";
	    		System.out.println("query before update :" + query1);
	    		PreparedStatement st=con.prepareStatement(query1);
	    		st.executeUpdate();
	    		if(i<j) {
	    		query1 = query;
	    		System.out.println("query after update : "+query1);
	    		}
	    	}
	}
	}catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   		RequestDispatcher rd1 = req.getRequestDispatcher("TT_Education_details.html");
   	    rd1.forward(req, res);
   	    session.setAttribute("emp_id",employee_id);

	}

}
