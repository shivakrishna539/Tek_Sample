package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;


@WebServlet("/EmployeeAddress")
public class EmployeeAddress extends HttpServlet {
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
        //System.out.println(employee_id1 + employee_id2);
        if(employee_id1 != 0) {
        	employee_id = employee_id1;
        }
        if(employee_id2 != 0) {
        	employee_id = employee_id2;
        }*/
	    System.out.println(employee_id);
	    
	    String Address_Temporary=req.getParameter("pradd");
		String Address_Permanent=req.getParameter("pmname");
		String Email_ID=req.getParameter("email");
		String Email_ID_Alt=req.getParameter("alt_email");
		String Phone_1=req.getParameter("mobile");
		String Phone_2=req.getParameter("alt_mobile");
		String Emergency_Contact_No=req.getParameter("alt_mobile");
	    
		//System.out.println(Address_Temporary+Address_Permanent+Email_ID+Email_ID_Alt+Phone_1+Phone_2+Emergency_Contact_No);
		//Created on date
	    Date createdOn = null;
	   
		//created by
		String createdBy = null;
		
		Date updatedOn = null;
	    String updatedBy = null;
	   // int employee_family_id = employee_id;
	    
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
   	    	PreparedStatement st = con.prepareStatement("INSERT INTO \"tektree\".\"employee_address\""
   	    		+ "(employee_id,address_temporary,address_permanent,email_id,email_id_alt,phone_1,phone_2,"
   	    		+ "emergency_contact_no,created_on,created_by) "
   	    		+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
   	    		//+ "VALUES("+employee_id+",'"+Address_Temporary+"','"+Address_Permanent+"','"+Email_ID+"',"
   	    		//+ "'"+Email_ID_Alt+"','"+Phone_1+"','"+Phone_2+"','"+Emergency_Contact_No+"','"+createdOn+"','"+createdBy+"');";
   	    	//System.out.println(query);
   	    	st.setInt(1,employee_id);
   	    	st.setString(2,Address_Temporary);
   	    	st.setString(3,Address_Permanent);
   	    	st.setString(4,Email_ID);
   	    	st.setString(5,Email_ID_Alt);
   	    	st.setString(6,Phone_1);
   	    	st.setString(7,Phone_2);
   	    	st.setString(8,Emergency_Contact_No);
   	    	st.setDate(9,createdOn);
   	    	st.setString(10,createdBy);
   	    
   	    	int i=st.executeUpdate();
   	    	st.close();
   	    
   	    	//int i = st1.executeUpdate(query);
   	    	//System.out.println(i);
        
   	    	//st1.close();
   	    	//System.out.println("Inserted");
   	    	session.setAttribute("emp_id",employee_id);
   	    	//System.out.println(session.getAttribute("emp_id"));
   	    if(i>0)
   	    {
   	    	RequestDispatcher rd=req.getRequestDispatcher("AddressList");
			rd.forward(req, res);
   	    }
   	    }
   	    catch(Exception e){
   	    	e.printStackTrace();
   	    }
}
}