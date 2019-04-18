package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;


@WebServlet("/UpdateEmployeeDetails")
public class UpdateEmployeeDetails extends HttpServlet {
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
        int emp_id = Integer.parseInt((String)session.getAttribute("emp_id"));
        
        //Database Connection
        Connection con = null;
		try {
			con = ConnectionManager.getConnection();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	    String employee_code = "";
	    String first_name = "";
	    String middle_name = "";
	    String last_name = "";
	    
   	    java.util.Date dob = null;
   		String aadhar_no = "";
   		String pan_no = "";
   		String passport_no = "";
   		String blood_group = "";
   		String is_married = "";
   		String is_external = "";
   		String background_verified_by = "";
   		String employee_type = "";
   		String designation="";
   		String skill = "";
   		String current_location = "";
   		String total_experience = "";
   		int ctc = 0;
   		java.util.Date doj = null;
   		String gender = "";
   		
   		employee_code = req.getParameter("employee_code");
   		first_name = req.getParameter("first_name");
   		middle_name = req.getParameter("middle_name");
   		last_name = req.getParameter("last_name");
	    aadhar_no = req.getParameter("aadhar_no");
	    pan_no = req.getParameter("pan_no");
	    passport_no = req.getParameter("passport_no");
	    blood_group = req.getParameter("blood_group");
	    is_married = req.getParameter("marital_status");
	    java.util.Date anniversary_date=null;
	    try {
	    dob=Date.valueOf(req.getParameter("dob"));
	    anniversary_date = Date.valueOf(req.getParameter("anniversary_date"));
	    }
	    catch(Exception e) {
	    	
	    } 
	    doj=Date.valueOf(req.getParameter("doj"));
	    ctc = Integer.parseInt(req.getParameter("ctc"));
	    is_external = req.getParameter("is_external");
	    background_verified_by = req.getParameter("background_verified_by");
	    employee_type = req.getParameter("employee_type");
	    designation=req.getParameter("designation"); 
	    skill = req.getParameter("skill");
	    current_location = req.getParameter("current_location");
	    total_experience = req.getParameter("total_experience");
	    gender = req.getParameter("gender");
   	   
   	    String update = "UPDATE \"tektree\".\"employee\" SET ";
   	    
   	    Date updatedOn = new Date(session.getCreationTime());
	    String updatedBy = (String)session.getAttribute("user");
   	    
   	    //employee_code
   	    if( employee_code != ""){
   	    	update = update + "employee_code='"+employee_code+"',";
   	    }
   	   
   	    //first_name
   	    if( first_name != "" ){	
   	    	update = update + "first_name='"+first_name+"',";
	    }
   	  
   	    //middle_name
   	    if( middle_name != ""){
   	    	update = update + "middle_name='"+middle_name+"',";
	    }
   	    
   	    //last_name
   	    if( last_name != ""){
   	    	update = update + "last_name='"+last_name+"',";
   	    }
   	    
   	    //dob
   	    if( dob != null){
   	    	update = update + "dob='"+dob+"',";
   	    }
   	   
   	    //aadhar_no
   	    if( aadhar_no != "" ){	
   	    	update = update + "aadhar_no='"+aadhar_no+"',";
	    }
   	  
   	    //pan_no
   	    if( pan_no != ""){
   	    	update = update + "pan_no='"+pan_no+"',";
	    }
   	    
   	    //passport_no
   	    if( passport_no != ""){
   	    	update = update + "passport_no='"+passport_no+"',";
   	    }
   	   
   	    //blood_group
   	    if( blood_group != "" ){	
   	    	update = update + "blood_group='"+blood_group+"',";
	    }
   	  
   	    //is_married
   	    if( is_married != null){
   	    	update = update + "is_married='"+is_married+"',";
	    }
   	    
   	    //anniversary_date
   	    if( anniversary_date != null){
   	    	update = update + "anniversary_date='"+anniversary_date+"',";
   	    }
   	   
   	    //doj
   	    if( doj != null ){	
   	    	update = update + "doj='"+doj+"',";
	    }
   	  
   	    //is_external
   	    if( is_external != ""){
   	    	update = update + "is_external='"+is_external+"',";
	    }
   	    
    	//background_verified_by
   	    if( background_verified_by != ""){
   	    	update = update + "background_verified_by='"+background_verified_by+"',";
   	    }
   	    //employee_type
   	    if( employee_type != "" ){	
   	    	update = update + "employee_type='"+employee_type+"',";
	    }
   	  
   	    //designation
   	    if( designation != ""){
   	    	update = update + "designation='"+designation+"',";
	    }
   	    
     	//skill
   	    if( skill != "" ){	
   	    	update = update + "skill='"+skill+"',";
	    }
   	  
   	    //current_location
   	    if( current_location != ""){
   	    	update = update + "current_location='"+current_location+"',";
	    }
   	    
    	//total_experience
   	    if( total_experience != ""){
   	    	update = update + "total_experience='"+total_experience+"',";
   	    }
   	    //ctc
   	    if( ctc != 0 ){	
   	    	update = update + "ctc='"+ctc+"',";
	    }
   	  
   	    //gender
   	    if( gender != null){
   	    	update = update + "gender='"+gender+"',";
	    }
   	    
   	    //updatedOn
   	    if( updatedOn != null){
   	    	update = update + "last_updated_on='"+updatedOn+"',";
   	    }
   	    
   	    //updatedBy
   	    if( updatedBy != ""){
   	    	update = update + "last_updated_by='"+updatedBy+"',";
	    }
   	     
   	    update = update.substring(0, update.length()-1);
   	    
   	    update = update + " WHERE employee_id = "+emp_id+"";
   	    System.out.println(update);
   	    
   	    try {
			Statement st1 = con.createStatement();
			int i = st1.executeUpdate(update);
			
			System.out.println(i);
			
			pw.println("Your update is succesfull");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	    
   	    
   	    
   	    
   	    
   	    
   	    
}
}